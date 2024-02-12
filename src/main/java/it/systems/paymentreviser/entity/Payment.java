package it.systems.paymentreviser.entity;

public class Payment {
	
	private final long id;
	private final String amount;
	private final ValidCurrencies currency;
	
	public Payment(long id, String amount, ValidCurrencies currency) {
		this.id = id;
		this.amount = amount;
		this.currency = currency;
	}
}
