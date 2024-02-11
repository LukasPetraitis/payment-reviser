package it.systems.paymentreviser.service;

import it.systems.paymentreviser.entity.Payment;

public interface PaymentService {
	boolean createCase(Payment payment);
}
