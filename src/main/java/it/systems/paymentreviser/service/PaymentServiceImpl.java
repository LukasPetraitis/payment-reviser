package it.systems.paymentreviser.service;

import it.systems.paymentreviser.entity.Case;
import it.systems.paymentreviser.entity.PaymentDTO;
import it.systems.paymentreviser.repository.MemoryRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
	private MemoryRepository memoryRepository;
	
	public PaymentServiceImpl(MemoryRepository memoryRepository) {
		this.memoryRepository = memoryRepository;
	}
	
	@Override
	public Case createCase(PaymentDTO paymentDTO) {
		Case paymentCase = new Case(paymentDTO);
		return memoryRepository.save(paymentCase);
	}
}
