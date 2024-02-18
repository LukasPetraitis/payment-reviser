package it.systems.paymentreviser.controller;

import it.systems.paymentreviser.dto.PaymentDTO;
import it.systems.paymentreviser.dto.UnresolvedCasesDataDTO;
import it.systems.paymentreviser.entity.Case;
import it.systems.paymentreviser.enums.ResolutionStatus;
import it.systems.paymentreviser.exception.PaymentDataInvalidException;
import it.systems.paymentreviser.service.PaymentService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static it.systems.paymentreviser.dto.DTOUtils.isPaymentDataEmpty;

@RestController
@RequestMapping("cases")
public class CaseController {
	private final PaymentService paymentService;
	
	public CaseController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
		@PostMapping
		public Case create(@RequestBody PaymentDTO paymentDTO) {
			if (isPaymentDataEmpty(paymentDTO)) {
				throw new PaymentDataInvalidException("All data fields for a payment are required to create a case");
			}
			return paymentService.createCase(paymentDTO);
		}
		
		@GetMapping
		public List<Case> getAllCases() {
			return paymentService.getAll();
		}
	
		@PutMapping("{id}")
		public Case resolveCase(@PathVariable Integer id, @RequestBody ResolutionStatus resolutionStatus) {
			return paymentService.resolveCase(id, resolutionStatus);
		}
		
		@GetMapping("unresolved-cases-data")
		public UnresolvedCasesDataDTO getUnresolvedCasesData() {
			return paymentService.getUnresolvedCasesData();
		}
}
