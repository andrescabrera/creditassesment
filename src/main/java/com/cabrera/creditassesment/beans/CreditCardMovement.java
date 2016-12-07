package com.cabrera.creditassesment.beans;

import java.util.Date;

public class CreditCardMovement {

	public Date date;
	public String company;
	public Amount amount;

	public CreditCardMovement(Date date, String company, Amount amount) {
		super();
		this.date = date;
		this.company = company;
		this.amount = amount;
	}
	
	public Date getDate() {
		return date;
	}

	public String getCompany() {
		return company;
	}

	public Amount getAmount() {
		return amount;
	}
}