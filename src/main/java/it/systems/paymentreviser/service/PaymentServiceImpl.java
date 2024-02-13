package it.systems.paymentreviser.service;

import it.systems.paymentreviser.entity.Case;
import it.systems.paymentreviser.dto.CaseResolveDTO;
import it.systems.paymentreviser.dto.PaymentDTO;
import it.systems.paymentreviser.entity.UnresolvedAmountCount;
import it.systems.paymentreviser.repository.MemoryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
	private final MemoryRepository memoryRepository;
	
	public PaymentServiceImpl(MemoryRepository memoryRepository) {
		this.memoryRepository = memoryRepository;
	}
	
	@Override
	public Integer createCase(PaymentDTO paymentDTO) {
		Case paymentNormalCase = new Case(paymentDTO);
		return memoryRepository.save(paymentNormalCase);
	}
	
	@Override
	public List<Case> getAll() {
		return new ArrayList<Case>(memoryRepository.findAll());
	}
	
	@Override
	public boolean resolveCase(CaseResolveDTO caseResolveDTO) {
		Optional<Case> caseForResolution = Optional.of(memoryRepository.findOne(caseResolveDTO.caseId()));
		
		if(!caseForResolution.isPresent()) {
			return false;
		}
		
		memoryRepository.save(caseForResolution.get());
		return caseForResolution.get().changeResolutionStatus(caseResolveDTO.resolutionStatus());
	}
	
	public BigDecimal getUnresolvedAmount() {
		List<Case> amountCounts = new ArrayList<>(memoryRepository.findAll());

		BigDecimal totalAmount = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
		for(UnresolvedAmountCount amount : amountCounts) {
			totalAmount = totalAmount.add(amount.getUnresolvedAmount());
		}
		return totalAmount;
	}
}
