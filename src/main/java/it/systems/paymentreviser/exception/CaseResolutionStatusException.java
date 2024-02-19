package it.systems.paymentreviser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CaseResolutionStatusException extends ResponseStatusException {
	
	public CaseResolutionStatusException(String caseType) {
		super(HttpStatus.BAD_REQUEST, String.format("Resolution status cannot be changed anymore for the %s", caseType));
	}
	
	public CaseResolutionStatusException(Integer id, String caseType) {
		super(HttpStatus.BAD_REQUEST, String.format("Resolution status cannot be changed anymore for the %s with id %s", caseType ,id));
	}
}
