package it.systems.paymentreviser.service;

import it.systems.paymentreviser.entity.PaymentDTO;

public interface PaymentService {
	boolean createCase(PaymentDTO payment);
}
