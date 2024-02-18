package it.systems.paymentreviser.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;



public enum ResolutionStatus {
	UNRESOLVED,
	RESUBMIT,
	RETURN;
	
	@JsonCreator
	public static ResolutionStatus fromJson(@JsonProperty("resulutionstatus") String name) {
		return valueOf(name);
	}
}
