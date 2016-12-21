/**
 * 
 */
package com.cabrera.creditassesment.controllers;

import java.util.concurrent.ExecutionException;

import com.cabrera.creditassesment.beans.Amount;
import com.cabrera.creditassesment.beans.CreditAssesment;
import com.cabrera.creditassesment.beans.Customer;
import com.cabrera.creditassesment.engine.CreditAssesmentEngine;
import com.cabrera.creditassesment.engine.CreditAssesmentEngineImpl;
import com.cabrera.creditassesment.services.CreditAssesmentService;
import com.cabrera.creditassesment.services.CreditAssesmentServiceImpl;

/**
 * Credit Assesment Controller
 * 
 * @author L0656968
 */
public class CreditAssesmentController {

	private CreditAssesmentService creditAssesmentService;

	public CreditAssesmentController() {
		CreditAssesmentEngine engine = new CreditAssesmentEngineImpl();
		this.creditAssesmentService = new CreditAssesmentServiceImpl(engine);
	}

	public CreditAssesment evaluate(Customer customer, Amount amount) throws InterruptedException, ExecutionException {
		return this.creditAssesmentService.evaluate(customer, amount).blockingGet();
	}

}
