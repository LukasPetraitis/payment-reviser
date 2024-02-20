package it.systems.paymentreviser.entity;

import it.systems.paymentreviser.enums.ValidCurrency;

import java.math.BigDecimal;
import java.math.RoundingMode;

	public class Payment {
		
		private final long id;
		private final BigDecimal amount;
		private final ValidCurrency currency;
		
		public Payment(long id, BigDecimal amount, ValidCurrency currency) {
			this.id = id;
			this.amount = amount.setScale(2, RoundingMode.HALF_UP);
			this.currency = currency;
		}
		
		BigDecimal getAmount() {
			return amount;
		}
	}
