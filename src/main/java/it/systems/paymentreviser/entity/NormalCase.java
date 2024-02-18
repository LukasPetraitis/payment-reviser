package it.systems.paymentreviser.entity;

import it.systems.paymentreviser.dto.PaymentDTO;
import it.systems.paymentreviser.enums.ResolutionStatus;
import it.systems.paymentreviser.exception.CaseResolutionStatusException;

public class NormalCase extends Case{
	public NormalCase(PaymentDTO paymentDTO) {
		super(paymentDTO);
	}
	
	@Override
	public void changeResolutionStatus(ResolutionStatus resolution) {
		if (super.resolution != ResolutionStatus.UNRESOLVED ) {
			throw new CaseResolutionStatusException(super.id, super.paymentType);
		}
		this.resolution = resolution;
	}
}