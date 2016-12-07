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

	private Customer customer;
	
	public MasterCardService(Customer customer) {
		super();
		this.customer = customer;
	}

	@Override
	public Boolean call() throws Exception {
		System.out.println("calling masterCard for customer " + customer);
		// WS Connection Latency Time Simulation
		try {
			Thread.sleep((long) (Math.random() * 60000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return Math.random() < 0.5;
	}
	
}
