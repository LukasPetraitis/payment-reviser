package it.systems.paymentreviser.service;

import it.systems.paymentreviser.dto.PaymentDTO;
import it.systems.paymentreviser.dto.UnresolvedCasesDataDTO;
import it.systems.paymentreviser.entity.Case;
import it.systems.paymentreviser.enums.ResolutionStatus;

import java.util.List;

public interface PaymentService {
	
	Case createCase(PaymentDTO paymentDTO);
	
	List<Case> getAll();
	
	Case resolveCase(Integer id, ResolutionStatus resolutionStatus);
	
	UnresolvedCasesDataDTO getUnresolvedCasesData();
}
