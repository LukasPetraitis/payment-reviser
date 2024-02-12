package it.systems.paymentreviser.entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Case {
	private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
	private static final AtomicInteger UNPROCESSED_PAYMENTS_COUNTER = new AtomicInteger(0);
	private final Integer id;
	private ResolutionStatuses resolution;
	private final Payment payment;
	
	public Case(PaymentDTO paymentDTO) {
		this.id = ID_GENERATOR.getAndIncrement();
		this.payment = new Payment(paymentDTO.id(), paymentDTO.amount(), paymentDTO.currency());
		UNPROCESSED_PAYMENTS_COUNTER.incrementAndGet();
	}
	
	public boolean resolveCase(ResolutionStatuses resolution) {
		if (ResolutionStatuses.RESUBMIT == this.resolution || ResolutionStatuses.RETURN== this.resolution ) {
			return false;
		}
		this.resolution = resolution;
		UNPROCESSED_PAYMENTS_COUNTER.decrementAndGet();
		return true;
	}
	
	public Integer getId() {
		return id;
	}
	
}
