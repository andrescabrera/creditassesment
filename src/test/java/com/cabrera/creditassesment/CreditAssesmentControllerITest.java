/**
 * 
 */
package com.cabrera.creditassesment;

import static org.junit.Assert.assertFalse;

import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

import com.cabrera.creditassesment.beans.Amount;
import com.cabrera.creditassesment.beans.CreditAssesment;
import com.cabrera.creditassesment.beans.Customer;
import com.cabrera.creditassesment.beans.Person;
import com.cabrera.creditassesment.controllers.CreditAssesmentController;

public class CreditAssesmentControllerITest {

	CreditAssesmentController cac = new CreditAssesmentController();
	
	/**
	 * Test method for {@link com.cabrera.creditassesment.CreditAssesmentController#evaluate(com.cabrera.creditassesment.beans.Customer, com.cabrera.creditassesment.beans.Amount)}.
	 */
	@Test
	public void testEvaluate() {
		Customer c = new Person("Andr�s", "Cabrera Laden");
		Amount a = new Amount(50d, Currency.getInstance(Locale.UK));
		CreditAssesment ca = cac.evaluate(c, a);
		assertFalse(ca.isValid());
	}

}
