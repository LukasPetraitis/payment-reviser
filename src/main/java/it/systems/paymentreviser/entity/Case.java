package it.systems.paymentreviser.entity;

import it.systems.paymentreviser.dto.PaymentDTO;
import it.systems.paymentreviser.enums.PaymentType;
import it.systems.paymentreviser.enums.ResolutionStatus;
import it.systems.paymentreviser.enums.ValidCurrency;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;

public class Case implements UnresolvedAmountCount {
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
	private static AtomicInteger UNPROCESSED_PAYMENTS_COUNTER = new AtomicInteger(0);
	private final Integer id;
	private ResolutionStatus resolution;
	private final PaymentType paymentType;
	private final Payment payment;

	public Case(PaymentDTO paymentDTO) {
		id = ID_GENERATOR.getAndIncrement();
		this.payment = new Payment(paymentDTO.id(), paymentDTO.amount(), paymentDTO.currency());
		this.paymentType = paymentDTO.paymentType();
		UNPROCESSED_PAYMENTS_COUNTER.incrementAndGet();
	}
	public Integer getId() {
		return id;
	}
	
	public boolean changeResolutionStatus(ResolutionStatus resolution) {
		if (this.resolution != null ) {
			return false;
		} else if (paymentType == PaymentType.NORMAL) {
			this.resolution = resolution;
			UNPROCESSED_PAYMENTS_COUNTER.decrementAndGet();
			return true;
		} else if (paymentType == PaymentType.RETURNED) {
			if (ResolutionStatus.RESUBMIT == resolution) {
				this.resolution = resolution;
				UNPROCESSED_PAYMENTS_COUNTER.decrementAndGet();
				return true;
			}
		}
		return false;
	}
	
	public static Integer unresolvedCasesCount() {
		return UNPROCESSED_PAYMENTS_COUNTER.get();
	}
	
	@Override
	public BigDecimal getUnresolvedAmount() {
		if(resolution == null) {
			return payment.amount;
		}
		return BigDecimal.ZERO;
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
