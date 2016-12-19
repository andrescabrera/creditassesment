/**
 * 
 */
package com.cabrera.creditassesment.engine;

import io.reactivex.Maybe;
import io.reactivex.Observable;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.cabrera.creditassesment.beans.Amount;
import com.cabrera.creditassesment.beans.Business;
import com.cabrera.creditassesment.beans.CreditAssesment;
import com.cabrera.creditassesment.beans.CreditCardMovement;
import com.cabrera.creditassesment.beans.Customer;
import com.cabrera.creditassesment.beans.Person;

/**
 * Credit Assesment Engine Implementation
 * 
 * @author L0656968
 */
public class CreditAssesmentEngineImpl implements CreditAssesmentEngine {

	public CreditAssesmentEngineImpl() {}

	public Observable<CreditAssesment> evaluate(Customer customer, Amount amount) throws InterruptedException, ExecutionException {
		// Central Bank rules!
		Observable<Boolean> ruleOne = evaluateByName(customer);
		// Business rule from JDBC connection, microcredits!
		Observable<Boolean> ruleTwo = evaluateAmount(amount);
		// Business rule, no debts!
		Observable<Boolean> ruleThree = hasCreditCardDebts(customer);
		
		Observable<Boolean> rules = Observable.merge(ruleOne, ruleTwo, ruleThree);
		Maybe<Boolean> mayBeRules = rules.reduce((x, y) -> x && y);
		
		CreditAssesment assesment = new CreditAssesment(amount, customer, mayBeRules.blockingGet());
		
		return Observable.just(assesment);
	}

	/**
	 * Retrieve maxAmountPermitted from DB and compare with required amount
	 * 
	 * @param amount
	 *            Required Amount
	 * @return Single boolean Observable
	 */
	private Observable<Boolean> evaluateAmount(Amount amount) {
		Double maxAmountPermitted = 500d;
		return Observable.just(amount.getAmount() <= maxAmountPermitted);
	}

	private Observable<Boolean> hasCreditCardDebts(Customer customer)
			throws InterruptedException, ExecutionException {
		CreditCardBroker broker = new CreditCardBrokerServiceImpl(customer);
		Observable<List<CreditCardMovement>> creditCardMovementsObs = broker
				.call();
		Iterable<List<CreditCardMovement>> creditCardMovements = creditCardMovementsObs
				.blockingIterable();

		Boolean hasDebts = false;
		Iterator<List<CreditCardMovement>> creditCardMovIterator = creditCardMovements.iterator();
		while (creditCardMovIterator.hasNext()) {
			List<CreditCardMovement> movement = creditCardMovIterator.next();
			if (movement.size() > 0) {
				hasDebts = true;
			}
		}
		return Observable.just(hasDebts);
	}

	private Observable<Boolean> evaluateByName(Customer customer) {
		if (customer instanceof Person) {
			return evaluatePersonByName((Person) customer);
		} else {
			return evaluateBusinessByName((Business) customer);
		}
	}

	private Observable<Boolean> evaluatePersonByName(Person person) {
		if (person.getLastname().toLowerCase().endsWith("laden")) {
			return Observable.just(false);
		} else {
			return Observable.just(true);
		}
	}

	private Observable<Boolean> evaluateBusinessByName(Business business) {
		// Retrieve prohibited words from DB
		String prohibitedWord = "qaeda";

		if (business.getName().toLowerCase().endsWith(prohibitedWord)) {
			return Observable.just(false);
		} else {
			return Observable.just(true);
		}
	}

}
