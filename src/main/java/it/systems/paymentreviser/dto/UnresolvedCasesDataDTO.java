package it.systems.paymentreviser.dto;

import java.math.BigDecimal;

public record UnresolvedCasesDataDTO(Integer unresolvedCasesCount, BigDecimal unresolvedCasesAmount) {
}
