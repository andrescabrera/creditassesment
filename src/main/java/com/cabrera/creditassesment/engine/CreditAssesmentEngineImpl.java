/**
 * 
 */
package com.cabrera.creditassesment.engine;

import com.cabrera.creditassesment.beans.Amount;
import com.cabrera.creditassesment.beans.Business;
import com.cabrera.creditassesment.beans.CreditAssesment;
import com.cabrera.creditassesment.beans.Customer;
import com.cabrera.creditassesment.beans.Person;
import com.cabrera.creditassesment.services.broker.CreditCardService;

/**
 * Credit Assesment Engine Implementation
 * 
 * @author L0656968
 */
public class CreditAssesmentEngineImpl implements CreditAssesmentEngine {

	private CreditCardService masterCardService;
	private CreditCardService visaService;

	public CreditAssesmentEngineImpl(CreditCardService masterCardService,
			CreditCardService visaService) {
		this.masterCardService = masterCardService;
		this.visaService = visaService;
	}

	public CreditAssesment evaluate(Customer customer, Amount amount) {
		Boolean ruleOne = evaluateByName(customer); //Central Bank rules!
		Boolean ruleTwo = amount.getAmount() <= 500d; //Business rule, microcredits!
		Boolean ruleThree = hasCreditCardDebts(customer); //Business rule, no debts!
		return new CreditAssesment(amount, customer, ruleOne && ruleTwo && ruleThree);
	}

	private Boolean hasCreditCardDebts(Customer customer) {
		Boolean masterCardDebts = masterCardService.hasCreditDebts(customer);
		Boolean visaDebts = visaService.hasCreditDebts(customer);
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
