package it.systems.paymentreviser.dto;

import it.systems.paymentreviser.enums.PaymentType;
import it.systems.paymentreviser.enums.ValidCurrency;

import java.math.BigDecimal;

public record PaymentDTO(long id, BigDecimal amount, ValidCurrency currency, PaymentType paymentType) {
}
