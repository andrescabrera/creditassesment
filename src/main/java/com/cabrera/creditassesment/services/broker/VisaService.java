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
 * @author L0656968
 *
 */
public class VisaService implements CreditCardService {

	private Customer customer;
	
	public VisaService(Customer customer) {
		super();
		this.customer = customer;
	}
	
	@Override
	public List<CreditCardMovement> call() throws Exception {
		List<CreditCardMovement> visaMovements = new ArrayList<CreditCardMovement>();
		System.out.println("calling visa for customer " + customer);

		// WS Connection Latency Time Simulation
		try {
			Thread.sleep((long) (Math.random() * 60000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		visaMovements.add(new CreditCardMovement(new Date(), "Visa International", new Amount(568.25d, Currency.getInstance(Locale.US))));
		return visaMovements;
	}

}
