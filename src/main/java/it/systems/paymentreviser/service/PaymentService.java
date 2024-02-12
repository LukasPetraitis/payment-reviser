package it.systems.paymentreviser.service;

import it.systems.paymentreviser.entity.Case;
import it.systems.paymentreviser.entity.PaymentDTO;

public interface PaymentService {
	Case createCase(PaymentDTO payment);
}
