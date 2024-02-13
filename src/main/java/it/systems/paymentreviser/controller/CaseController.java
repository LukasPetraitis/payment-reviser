package it.systems.paymentreviser.controller;

import it.systems.paymentreviser.dto.CaseResolveDTO;
import it.systems.paymentreviser.dto.PaymentDTO;
import it.systems.paymentreviser.dto.UnresolvedCasesDataDTO;
import it.systems.paymentreviser.entity.Case;
import it.systems.paymentreviser.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("case")
public class CaseController {
	private final PaymentService paymentService;
	
	public CaseController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
		@PutMapping("create")
		public ResponseEntity<String> create(@RequestBody PaymentDTO paymentDTO) {
			Integer caseId = paymentService.createCase(paymentDTO);
			return new ResponseEntity<>("Case with id " + caseId + " created for payment " + paymentDTO, HttpStatus.OK);
		}
		
		@GetMapping("all")
		public ResponseEntity<List<Case>> getAllCases() {
			return new ResponseEntity<>(paymentService.getAll(), HttpStatus.OK);
		}
	
		@PutMapping("resolve")
		public ResponseEntity<String> resolveCase(@RequestBody CaseResolveDTO caseResolveDTO) {
			boolean isStatusChanged = paymentService.resolveCase(caseResolveDTO);
			
			if (isStatusChanged) {
				return new ResponseEntity<>("Status for " + caseResolveDTO.caseId() + " to " + caseResolveDTO.resolutionStatus().toString() + " changed", HttpStatus.OK);
			}
			return new ResponseEntity<>("Status for " + caseResolveDTO.caseId() + " to " + caseResolveDTO.resolutionStatus().toString() + " change failed", HttpStatus.METHOD_NOT_ALLOWED);
		}
		
		@GetMapping("countUnresolved")
		public ResponseEntity<UnresolvedCasesDataDTO> countUnresolvedCases() {
			UnresolvedCasesDataDTO unresolvedCasesDataDTO = new UnresolvedCasesDataDTO(Case.unresolvedCasesCount(), paymentService.getUnresolvedAmount());
			return new ResponseEntity<>(unresolvedCasesDataDTO, HttpStatus.OK);
		}
}
