package com.cabrera.creditassesment.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.cabrera.creditassesment.beans.CreditCardMovement;
import com.cabrera.creditassesment.beans.Customer;
import com.cabrera.creditassesment.services.broker.CreditCardService;
import com.cabrera.creditassesment.services.broker.MasterCardService;
import com.cabrera.creditassesment.services.broker.VisaService;

public class CreditCardBrokerServiceImpl implements CreditCardBroker {

	private static final ExecutorService threadpool = Executors
			.newFixedThreadPool(2);
	
	private Customer customer;
	
	public CreditCardBrokerServiceImpl(Customer customer) {
		super();
		this.customer = customer;
	}

	@Override
	public List<List<CreditCardMovement>> call() throws Exception {
		List<List<CreditCardMovement>> creditCardMovements = new ArrayList<List<CreditCardMovement>>();
		
		CreditCardService masterCardService = new MasterCardService(customer);
		CreditCardService visaService = new VisaService(customer);

		Future<List<CreditCardMovement>> masterCardDebtsFuture = threadpool
				.submit(masterCardService);
		Future<List<CreditCardMovement>> visaDebtsFuture = threadpool.submit(visaService);

		List<CreditCardMovement> masterCardDebts = masterCardDebtsFuture.get(); 
		List<CreditCardMovement> visaDebts = visaDebtsFuture.get(); 
		
		creditCardMovements.add(masterCardDebts);
		creditCardMovements.add(visaDebts);
		
		return creditCardMovements;
	}

}
