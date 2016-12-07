/**
 * 
 */
package com.cabrera.creditassesment.engine;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.cabrera.creditassesment.beans.Amount;
import com.cabrera.creditassesment.beans.Business;
import com.cabrera.creditassesment.beans.CreditAssesment;
import com.cabrera.creditassesment.beans.Customer;
import com.cabrera.creditassesment.beans.Person;
import com.cabrera.creditassesment.services.broker.CreditCardService;
import com.cabrera.creditassesment.services.broker.MasterCardService;
import com.cabrera.creditassesment.services.broker.VisaService;

/**
 * Credit Assesment Engine Implementation
 * 
 * @author L0656968
 */
public class CreditAssesmentEngineImpl implements CreditAssesmentEngine {

	private static final ExecutorService threadpool = Executors
			.newFixedThreadPool(3);

	public CreditAssesmentEngineImpl() {
	}

	public CreditAssesment evaluate(Customer customer, Amount amount) throws InterruptedException, ExecutionException {
		// Central Bank rules!
		Boolean ruleOne = evaluateByName(customer);
		// Business rule from JDBC connection, microcredits!
		Boolean ruleTwo = amount.getAmount() <= 500d;
		// Business rule, no debts!
		Boolean ruleThree = hasCreditCardDebts(customer);
		return new CreditAssesment(amount, customer, ruleOne && ruleTwo
				&& ruleThree);
	}

	private Boolean hasCreditCardDebts(Customer customer) throws InterruptedException, ExecutionException {

		CreditCardService masterCardService = new MasterCardService(customer);
		CreditCardService visaService = new VisaService(customer);

		Future<Boolean> masterCardDebtsFuture = threadpool
				.submit(masterCardService);
		Future<Boolean> visaDebtsFuture = threadpool.submit(visaService);

		Boolean masterCardDebts = masterCardDebtsFuture.get(); 
		Boolean visaDebts = visaDebtsFuture.get(); 

		return masterCardDebts && visaDebts;
	}

	private Boolean evaluateByName(Customer customer) {
		if (customer instanceof Person) {
			return evaluatePersonByName((Person) customer);
		} else {
			return evaluateBusinessByName((Business) customer);
		}
	}

	private Boolean evaluatePersonByName(Person person) {
		if (person.getLastname().toLowerCase().endsWith("laden")) {
			return false;
		} else {
			return true;
		}
	}

	private Boolean evaluateBusinessByName(Business business) {
		if (business.getName().toLowerCase().endsWith("qaeda")) {
			return false;
		} else {
			return true;
		}
	}

}
