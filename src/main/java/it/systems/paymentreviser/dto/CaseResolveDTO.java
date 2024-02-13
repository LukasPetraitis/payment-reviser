package it.systems.paymentreviser.dto;

import it.systems.paymentreviser.enums.ResolutionStatus;

public record CaseResolveDTO(Integer caseId, ResolutionStatus resolutionStatus) {
}
