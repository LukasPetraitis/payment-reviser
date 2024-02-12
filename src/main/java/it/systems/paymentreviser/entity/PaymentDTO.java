package it.systems.paymentreviser.entity;

public record PaymentDTO(long id, String amount, ValidCurrencies currency) {
}
