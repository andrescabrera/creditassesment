/**
 * 
 */
package com.cabrera.creditassesment.engine;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;

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

	public CreditAssesmentEngineImpl() {
	}

	public Single<CreditAssesment> evaluate(Customer customer, Amount amount)
			throws InterruptedException, ExecutionException {
		// Central Bank rules!
		Single<Boolean> ruleOne = evaluateByName(customer);
		// Business rule from JDBC connection, microcredits!
		Single<Boolean> ruleTwo = evaluateAmount(amount);
		// Business rule, no debts!
		Single<Boolean> ruleThree = hasCreditCardDebts(customer);

		Flowable<Boolean> rules = Single.merge(ruleOne, ruleTwo, ruleThree);

		Single<CreditAssesment> assesmentObs = Single.create(caEmitter -> {
			rules.reduce((ruleX, ruleY) -> ruleX && ruleY).subscribe(rulesRedux -> {
				caEmitter.onSuccess(new CreditAssesment(amount, customer, rulesRedux));
			});
		});

		return assesmentObs;
	}

	/**
	 * Retrieve maxAmountPermitted from DB and compare with required amount
	 * 
	 * @param amount
	 *            Required Amount
	 * @return Single boolean Observable
	 */
	private Single<Boolean> evaluateAmount(Amount amount) {
		Double maxAmountPermitted = 500d;
		return Single.just(amount.getAmount() <= maxAmountPermitted);
	}

	private Single<Boolean> hasCreditCardDebts(Customer customer)
			throws InterruptedException, ExecutionException {
		CreditCardBroker broker = new CreditCardBrokerServiceImpl(customer);
		return broker.call().any(x -> x.size() > 0);
	}

	private Single<Boolean> evaluateByName(Customer customer) {
		if (customer instanceof Person) {
			return evaluatePersonByName((Person) customer);
		} else {
			return evaluateBusinessByName((Business) customer);
		}
	}

	private Single<Boolean> evaluatePersonByName(Person person) {
		if (person.getLastname().toLowerCase().endsWith("laden")) {
			return Single.just(false);
		} else {
			return Single.just(true);
		}
	}

	private Single<Boolean> evaluateBusinessByName(Business business) {
		// Retrieve prohibited words from DB
		String prohibitedWord = "qaeda";

		if (business.getName().toLowerCase().endsWith(prohibitedWord)) {
			return Single.just(false);
		} else {
			return Single.just(true);
		}
	}

}
