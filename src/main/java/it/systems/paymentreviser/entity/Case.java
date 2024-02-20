package it.systems.paymentreviser.entity;

import it.systems.paymentreviser.enums.ResolutionStatus;

import java.math.BigDecimal;

public abstract class Case {
	protected Integer id;
	protected ResolutionStatus resolution;
	protected final Payment payment;

	protected Case(Payment payment) {
		this.payment = payment;
		this.resolution = ResolutionStatus.UNRESOLVED;
	}
	
	public abstract void changeResolutionStatus(ResolutionStatus resolution);
	
	public ResolutionStatus getResolution() {
		return resolution;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public BigDecimal getPaymentAmount() {
		return payment.getAmount();
	}
	
}
