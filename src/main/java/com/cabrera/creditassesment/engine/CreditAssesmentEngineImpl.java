/**
 * 
 */
package com.cabrera.creditassesment.engine;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.cabrera.creditassesment.beans.Amount;
import com.cabrera.creditassesment.beans.Business;
import com.cabrera.creditassesment.beans.CreditAssesment;
import com.cabrera.creditassesment.beans.CreditCardMovement;
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
			.newFixedThreadPool(2);

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
		CreditCardBroker broker = new CreditCardBrokerServiceImpl(customer);
		Future<List<List<CreditCardMovement>>> creditCardMovementsFuture = threadpool.submit(broker);
		List<List<CreditCardMovement>> creditCardMovements = creditCardMovementsFuture.get();
		return creditCardMovements.size() == 2 && (creditCardMovements.get(1).size() > 0 || creditCardMovements.get(2).size() > 0);
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
