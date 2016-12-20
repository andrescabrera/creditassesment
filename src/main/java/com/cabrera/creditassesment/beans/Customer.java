/**
 * 
 */
package com.cabrera.creditassesment.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Customer
 * 
 * @author L0656968
 */
public abstract class Customer {

	private Date registrationDate;
	private Set<CreditAssesment> assesments;
	
	public Customer() {
		this.assesments = new HashSet<CreditAssesment>();
		this.registrationDate = new Date();
	}
	
	public final void addCreditAssesment(CreditAssesment e) {
		assesments.add(e);
	}
	
	public final Integer countAssesments() {
		return this.assesments.size();
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [registrationDate=").append(registrationDate)
				.append(", assesments=").append(assesments)
				.append(", toString()=").append(super.toString()).append("]");
		return builder.toString();
	}
	
	
}
