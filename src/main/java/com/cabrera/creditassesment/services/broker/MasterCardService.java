/**
 * 
 */
package com.cabrera.creditassesment.services.broker;

import com.cabrera.creditassesment.beans.Customer;

/**
 * @author L0656968
 * 
 */
public class MasterCardService implements CreditCardService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cabrera.creditassesment.services.broker.CreditCardService#hasCreditDebts
	 * (com.cabrera.creditassesment.beans.Customer)
	 */
	public Boolean hasCreditDebts(Customer customer) {

		// WS Connection Latency Time Simulation
		try {
			Thread.sleep((long) (Math.random() * 60000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return Math.random() < 0.5;
	}

}
