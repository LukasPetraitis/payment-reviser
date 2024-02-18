package it.systems.paymentreviser.service;

import it.systems.paymentreviser.dto.UnresolvedCasesDataDTO;
import it.systems.paymentreviser.entity.Case;
import it.systems.paymentreviser.dto.PaymentDTO;
import it.systems.paymentreviser.entity.NormalCase;
import it.systems.paymentreviser.entity.ReturnedCase;
import it.systems.paymentreviser.enums.ResolutionStatus;
import it.systems.paymentreviser.exception.NoSuchPaymentTypeException;
import it.systems.paymentreviser.repository.CaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
	private final CaseRepository caseRepository;
	
	public PaymentServiceImpl(CaseRepository caseRepository) {
		this.caseRepository = caseRepository;
	}
	
	@Override
	public Case createCase(PaymentDTO paymentDTO) {
		Case unresolvedCase = switch (paymentDTO.paymentType()) {
			case NORMAL -> new NormalCase(paymentDTO);
			case RETURNED -> new ReturnedCase(paymentDTO);
			default -> throw new NoSuchPaymentTypeException("Case with such payment type cannot be created");
		};
		return caseRepository.save(unresolvedCase);
	}
	
	@Override
	public List<Case> getAll() {
		return new ArrayList<>(caseRepository.findAll());
	}
	
	@Override
	public Case resolveCase(Integer id, ResolutionStatus resolutionStatus) {
		Case caseForResolution = caseRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Case with id %s does not exist", id)));
		caseForResolution.changeResolutionStatus(resolutionStatus);
		return caseRepository.update(caseForResolution);
	}
	
	@Override
	public UnresolvedCasesDataDTO getUnresolvedCasesData() {
		return caseRepository.getUnresolvedCasesData();
	}
}
