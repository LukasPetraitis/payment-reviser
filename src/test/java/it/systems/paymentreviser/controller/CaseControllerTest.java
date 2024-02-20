package it.systems.paymentreviser.controller;

import it.systems.paymentreviser.dto.PaymentDTO;
import it.systems.paymentreviser.dto.UnresolvedCasesDataDTO;
import it.systems.paymentreviser.entity.Case;
import it.systems.paymentreviser.enums.PaymentType;
import it.systems.paymentreviser.enums.ResolutionStatus;
import it.systems.paymentreviser.enums.ValidCurrency;
import it.systems.paymentreviser.exception.PaymentDataInvalidException;
import it.systems.paymentreviser.repository.CaseRepository;
import it.systems.paymentreviser.service.PaymentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


class CaseControllerTest {
	
	private CaseRepository fakeCaseRepository;
	private CaseController caseController;
	
	@BeforeEach
	public void init() {
	    fakeCaseRepository = new CaseRepository() {
			
			private Integer ID_GENERATOR = 1;
			private Case aCase;
			
			@Override
			public Case save(Case entity) {
				Integer id = ID_GENERATOR++;
				entity.setId(id);
				Case previousCase = aCase;
				aCase = entity;
				return previousCase;
			}
			
			@Override
			public Case update(Case entity) {
				return aCase = entity;
			}
			
			@Override
			public Optional<Case> findById(Integer id) {
				return Optional.ofNullable(aCase);
			}
			
			@Override
			public List<Case> findAll() {
				return List.of(aCase);
			}
			
			@Override
			public UnresolvedCasesDataDTO getUnresolvedCasesData() {
				List<Case> unresolvedCasesList = Stream.of(aCase)
						.filter(aCase -> ResolutionStatus.UNRESOLVED.equals(aCase.getResolution()))
						.toList();
				
				BigDecimal totalAmountUnresolved = unresolvedCasesList.stream().map(Case::getPaymentAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
				
				return new UnresolvedCasesDataDTO(unresolvedCasesList.size(), totalAmountUnresolved);
			}
		};
		
		caseController = new CaseController(new PaymentServiceImpl(fakeCaseRepository));
	}
	
	@Test
	void create_givenPaymentDTOValid_thenIsSaved() {
		PaymentDTO paymentDTO = new PaymentDTO(1, new BigDecimal("12.05"), ValidCurrency.EUR, PaymentType.NORMAL);
		Case newCaseCreated = caseController.create(paymentDTO);
		Assertions.assertNull(newCaseCreated);
	}
	
	@Test
	void create_givenPaymentDTOInvalid_thenErrorThrown() {
		PaymentDTO paymentDTO = null;
		Exception exception = Assertions.assertThrows(PaymentDataInvalidException.class, () -> caseController.create(paymentDTO));
		Assertions.assertEquals("400 BAD_REQUEST \"All data fields for a payment are required to create a case\"", exception.getMessage());
	}
}