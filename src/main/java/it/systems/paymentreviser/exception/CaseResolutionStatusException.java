package it.systems.paymentreviser.exception;

import it.systems.paymentreviser.enums.PaymentType;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CaseResolutionStatusException extends ResponseStatusException {
	
	public CaseResolutionStatusException() {
		super(HttpStatus.BAD_REQUEST, String.format("Resolution status cannot be changed anymore for the %s Case", PaymentType.RETURNED));
	}
	
	public CaseResolutionStatusException(Integer id, PaymentType paymentType) {
		super(HttpStatus.BAD_REQUEST, String.format("Resolution status cannot be changed anymore for the %s Case %s",paymentType ,id));
	}
}
