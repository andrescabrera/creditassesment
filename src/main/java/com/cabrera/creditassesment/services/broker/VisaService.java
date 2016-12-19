/**
 * 
 */
package com.cabrera.creditassesment.services.broker;

import io.reactivex.Observable;

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
		double milis = Math.random() * 30000;
		System.out.println("calling visa for customer " + customer + " i will going to take " + milis/1000 + " seconds.");

		// WS Connection Latency Time Simulation
		try {
			Thread.sleep((long) (milis));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		visaMovements.add(new CreditCardMovement(new Date(), "Visa International", new Amount(568.25d, Currency
						.getInstance(Locale.US))));
		return visaMovements;
	}

}
