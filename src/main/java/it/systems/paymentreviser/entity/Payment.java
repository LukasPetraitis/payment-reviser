package it.systems.paymentreviser.entity;

public class Payment {
	
	private long id;
	private String amount;
	private String currency;
	
	public long getId() {
		return id;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public String getCurrency() {
		return currency;
	}
}
