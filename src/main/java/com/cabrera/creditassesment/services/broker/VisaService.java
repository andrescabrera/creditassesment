/**
 * 
 */
package com.cabrera.creditassesment.services.broker;

import io.reactivex.Observable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.cabrera.creditassesment.beans.Amount;
import com.cabrera.creditassesment.beans.CreditCardMovement;
import com.cabrera.creditassesment.beans.Customer;

/**
 * Visa Fake Service
 * 
 * @author L0656968
 */
public class VisaService implements CreditCardService {

	private Customer customer;
	
	public VisaService(Customer customer) {
		super();
		this.customer = customer;
	}
	
	@Override
	public Observable<List<CreditCardMovement>> call() {
		return Observable.defer(() -> Observable.just(createVisaMovements()));
	}

	/**
	 * The sync part. Will be called asynchronous
	 * 
	 * CreditCardMovements (Debts) from WS...
	 * 
	 * @return List of CreditCardMovements
	 */
	private List<CreditCardMovement> createVisaMovements() {
		List<CreditCardMovement> visaMovements = new ArrayList<CreditCardMovement>();
		double milis = Math.random() * 60000;
		Date startDate = new Date();
		System.out.println("Visa Service - Starting at: " + new Timestamp(startDate.getTime()));
		System.out.println("Visa Service - Thread name: " + Thread.currentThread().getName());
		System.out.println("Visa Service - Calling Visa for customer " + customer + ". it will going to take " + milis/1000 + " seconds.");

		// WS Connection Latency Time Simulation
		try {
			Thread.sleep((long) milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		visaMovements.add(new CreditCardMovement(new Date(), "Visa International", new Amount(568.25d, Currency
						.getInstance(Locale.US))));
		Date actualDate = new Date();
		long diff = (actualDate.getTime() - startDate.getTime()) / 1000;
		System.out.println("Visa service call Finished on: " + new Timestamp(actualDate.getTime()) + ". Took " + diff + " seconds.");
		
		return visaMovements;
	}

}
