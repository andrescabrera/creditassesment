/**
 * 
 */
package com.cabrera.creditassesment.services;

import io.reactivex.Observable;

import java.util.concurrent.ExecutionException;

import com.cabrera.creditassesment.beans.Amount;
import com.cabrera.creditassesment.beans.CreditAssesment;
import com.cabrera.creditassesment.beans.Customer;
import com.cabrera.creditassesment.engine.CreditAssesmentEngine;

/**
 * Credit Assesment Service Implementation
 * 
 * @author L0656968
 */
public class CreditAssesmentServiceImpl implements CreditAssesmentService {

	private CreditAssesmentEngine engine;

	public CreditAssesmentServiceImpl(CreditAssesmentEngine engine) {
		this.engine = engine;
	}

	public Observable<CreditAssesment> evaluate(Customer customer, Amount amount) throws InterruptedException, ExecutionException {
		return engine.evaluate(customer, amount);
	}

}
