package it.systems.paymentreviser.dto;

import it.systems.paymentreviser.enums.ResolutionStatus;

public record CaseDTO(Integer id, ResolutionStatus resolutionStatus, PaymentDTO paymentDTO) {
}
