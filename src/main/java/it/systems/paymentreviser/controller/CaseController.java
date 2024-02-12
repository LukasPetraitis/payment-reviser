package it.systems.paymentreviser.controller;

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
	public ResponseEntity<String> create(@RequestBody PaymentDTO payment) {
		
		if(payment.id() == 1) {
			return paymentService.createCase(payment) ?
					new ResponseEntity<>("Payment created", HttpStatus.OK) :
					new ResponseEntity<>("Payment creation failed", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>("Payment with such id is already present" , HttpStatus.CONFLICT);
	}
}
