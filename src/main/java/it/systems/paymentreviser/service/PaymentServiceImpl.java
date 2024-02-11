package it.systems.paymentreviser.service;

import it.systems.paymentreviser.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Override
	public boolean createCase(Payment payment) {
		return true;
	}
}
