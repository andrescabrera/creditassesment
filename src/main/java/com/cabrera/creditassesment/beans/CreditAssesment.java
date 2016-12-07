package com.cabrera.creditassesment.beans;

import java.util.Date;

/**
 * Credit Assesment
 * 
 * @author L0656968
 */
public class CreditAssesment {

	private Amount amount;
	private Customer customer;
	private Boolean valid;
	private Date date;
	
	public CreditAssesment(Amount amount, Customer customer, Boolean valid) {
		super();
		this.amount = amount;
		this.customer = customer;
		this.valid = valid;
		this.date = new Date();
	}

	public Date getDate() {
		return date;
	}

	public Amount getAmount() {
		return amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Boolean isValid() {
		return valid;
	}

	@Override
	public String toString() {
		return "CreditAssesment [amount=" + amount + ", customer=" + customer
				+ ", valid=" + valid + ", date=" + date + "]";
	}
}
