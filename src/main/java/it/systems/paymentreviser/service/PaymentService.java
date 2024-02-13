package it.systems.paymentreviser.service;

import it.systems.paymentreviser.dto.CaseResolveDTO;
import it.systems.paymentreviser.dto.PaymentDTO;
import it.systems.paymentreviser.entity.Case;
import it.systems.paymentreviser.entity.UnresolvedAmountCount;

import java.util.List;

public interface PaymentService extends UnresolvedAmountCount {
	
	Integer createCase(PaymentDTO payment);
	
	List<Case> getAll();
	
	boolean resolveCase(CaseResolveDTO caseResolveDTO);
	
}
