package it.systems.paymentreviser.entity;

import java.util.Objects;
import java.util.UUID;

public class Case {
	
	private final String id;
	private ResolutionStatuses resolution;
	private final Payment payment;
	
	private Case(String id, ResolutionStatuses resolution, Payment payment) {
		this.id = id;
		this.resolution = resolution;
		this.payment = payment;
	}
	
	public static Case createCase(PaymentDTO paymentDTO) {
		Payment payment = new Payment(paymentDTO.id(), paymentDTO.amount(), paymentDTO.currency());
		return new Case(UUID.randomUUID().toString(),
				ResolutionStatuses.UNRESOLVED,
				payment);
	}
	
	public boolean resolveCase(ResolutionStatuses resolution) {
		if(Objects.equals(this.resolution, ResolutionStatuses.RESUBMIT)) {
			throw new UnsupportedOperationException("Case with id: " + id + " is resubmited and cannot be modified");
		} else if (Objects.equals(resolution, "return")) {
			this.resolution = ResolutionStatuses.RETURN;
			System.out.println("Service for returned payments under construction at the moment :(");
			return true;
		} else if(ResolutionStatuses.RESUBMIT.equals(resolution)) {
			this.resolution = resolution;
			System.out.println("Case with id:" + id + "resubmitted");
			return true;
		}
		System.out.println("Case is still unprocessed");
		return false;
	}
	
	public String returnCaseIdCopy() {
		return new String(id);
	}
}
