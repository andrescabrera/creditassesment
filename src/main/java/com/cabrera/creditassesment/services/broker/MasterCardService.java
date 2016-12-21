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
	public Observable<List<CreditCardMovement>> call() {
		return Observable.defer(() -> Observable.just(createMasterCardMovements()));
	}

	/**
	 * Retrieve CreditCard Movements debts from WS... (the sync part).
	 * 
	 * That must be called from async way..
	 * 
	 * @return List of CreditCardMovements
	 */
	private List<CreditCardMovement> createMasterCardMovements() {
		List<CreditCardMovement> masterCardMovements = new ArrayList<CreditCardMovement>();
		double milis = Math.random() * 60000;
		Date startDate = new Date();
		System.out.println("MasterCard service call Thread name: " + Thread.currentThread().getName());
		System.out.println("MasterCard service call Starting at: " + new Timestamp(startDate.getTime()));
		System.out.println("calling masterCard for customer " + customer + " i will going to take " + milis/1000 + " seconds.");
		// WS Connection Latency Time Simulation
		try {
			Thread.sleep((long) milis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		masterCardMovements.add(new CreditCardMovement(new Date(), "Master Card", new Amount(5098.4225d, Currency.getInstance(Locale.UK))));
		masterCardMovements.add(new CreditCardMovement(new Date(), "Master Card", new Amount(123.123d, Currency.getInstance(Locale.UK))));
		masterCardMovements.add(new CreditCardMovement(new Date(), "Master Card", new Amount(543.435d, Currency.getInstance(Locale.UK))));
		Date actualDate = new Date();
		long diff = (actualDate.getTime() - startDate.getTime()) / 1000;
		System.out.println("MasterCard  service call Finished on: " + new Timestamp(actualDate.getTime()) + ". Took " + diff + " seconds.");
		return masterCardMovements;
	}

}
