package it.systems.paymentreviser.entity;

import it.systems.paymentreviser.dto.PaymentDTO;
import it.systems.paymentreviser.enums.PaymentType;
import it.systems.paymentreviser.enums.ResolutionStatus;
import it.systems.paymentreviser.enums.ValidCurrency;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Case {
	
	protected Integer id;
	protected ResolutionStatus resolution;
	protected final PaymentType paymentType;
	protected final Payment payment;

	protected Case(PaymentDTO paymentDTO) {
		this.payment = new Payment(paymentDTO.id(), paymentDTO.amount(), paymentDTO.currency());
		this.paymentType = paymentDTO.paymentType();
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
		return payment.amount;
	}
	
	public class Payment {
		
		private final long id;
		private final BigDecimal amount;
		private final ValidCurrency currency;
		
		public Payment(long id, BigDecimal amount, ValidCurrency currency) {
			this.id = id;
			this.amount = amount.setScale(2, RoundingMode.HALF_UP);
			this.currency = currency;
		}
	}
}
