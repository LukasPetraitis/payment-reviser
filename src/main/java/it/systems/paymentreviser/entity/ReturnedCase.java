package it.systems.paymentreviser.entity;

import it.systems.paymentreviser.enums.ResolutionStatus;
import it.systems.paymentreviser.exception.CaseResolutionStatusException;

public class ReturnedCase extends Case {
	public ReturnedCase(Payment paymentDTO) {
		super(paymentDTO);
	}
	
	@Override
	public void changeResolutionStatus(ResolutionStatus resolution) {
		if (super.resolution != ResolutionStatus.UNRESOLVED) {
			throw new CaseResolutionStatusException(super.id, this.getClass().getSimpleName());
		} else if (resolution == ResolutionStatus.RETURN) {
			throw new CaseResolutionStatusException(this.getClass().getSimpleName());
		} else if (ResolutionStatus.RESUBMIT == resolution) {
			this.resolution = resolution;
		}
	}
}
