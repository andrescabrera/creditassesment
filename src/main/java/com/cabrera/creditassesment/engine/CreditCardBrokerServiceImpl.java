package com.cabrera.creditassesment.engine;

import io.reactivex.Observable;

import java.util.List;

import com.cabrera.creditassesment.beans.CreditCardMovement;
import com.cabrera.creditassesment.beans.Customer;
import com.cabrera.creditassesment.services.broker.CreditCardService;
import com.cabrera.creditassesment.services.broker.MasterCardService;
import com.cabrera.creditassesment.services.broker.VisaService;

public class CreditCardBrokerServiceImpl implements CreditCardBroker {

	private Customer customer;
	
	public CreditCardBrokerServiceImpl(Customer customer) {
		super();
		this.customer = customer;
	}

	@Override
	public Observable<List<CreditCardMovement>> call() {
		CreditCardService masterCardService = new MasterCardService(customer);
		CreditCardService visaService = new VisaService(customer);

		Observable<List<CreditCardMovement>> masterCardDebtsObs = masterCardService.call();
		Observable<List<CreditCardMovement>> visaDebtsObs = visaService.call();

		return Observable.merge(masterCardDebtsObs, visaDebtsObs);
	}

}
