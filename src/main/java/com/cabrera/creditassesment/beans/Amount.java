package com.cabrera.creditassesment.beans;

import java.util.Currency;

public class Amount {

	public Double amount;
	public Currency currency;

	public Amount(Double amount, Currency currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "Amount [amount=" + amount + ", currency=" + currency + "]";
	}
}
