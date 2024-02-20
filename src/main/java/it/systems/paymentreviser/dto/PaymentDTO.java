package it.systems.paymentreviser.dto;

import it.systems.paymentreviser.enums.PaymentType;
import it.systems.paymentreviser.enums.ValidCurrency;

import java.math.BigDecimal;

public record PaymentDTO(Integer id, BigDecimal amount, ValidCurrency currency, PaymentType paymentType) {
}
