/**
 * 
 */
package com.cabrera.creditassesment.services.broker;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.cabrera.creditassesment.beans.Amount;
import com.cabrera.creditassesment.beans.CreditCardMovement;
import com.cabrera.creditassesment.beans.Customer;

/**
 * Master Card Service Implementation
 * 
 * @author L0656968
 */
public class MasterCardService implements CreditCardService {

	private Customer customer;
	
	public MasterCardService(Customer customer) {
		super();
		this.customer = customer;
	}

	@Override
	public List<CreditCardMovement> call() throws Exception {
		List<CreditCardMovement> masterCardMovements = new ArrayList<CreditCardMovement>();
		
		System.out.println("calling masterCard for customer " + customer);
		// WS Connection Latency Time Simulation
		try {
			Thread.sleep((long) (Math.random() * 60000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		masterCardMovements.add(new CreditCardMovement(new Date(), "Master Card", new Amount(5098.45d, Currency.getInstance(Locale.UK))));
		return masterCardMovements;
	}
	
}
