package it.systems.paymentreviser.controller;

import it.systems.paymentreviser.entity.Case;
import it.systems.paymentreviser.entity.PaymentDTO;
import it.systems.paymentreviser.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("case")
public class CaseController {
	private PaymentService paymentService;
	
	public CaseController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
		@PutMapping("/create")
		public ResponseEntity<String> create(@RequestBody PaymentDTO paymentDTO) {
			Case freshCase = paymentService.createCase(paymentDTO);
			return new ResponseEntity<>("Case with " + freshCase.getId() + " created for payment " + paymentDTO, HttpStatus.OK);
		}
		
}
