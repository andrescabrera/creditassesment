/**
 * 
 */
package com.cabrera.creditassesment.services;

import com.cabrera.creditassesment.beans.Amount;
import com.cabrera.creditassesment.beans.Customer;
import com.cabrera.creditassesment.beans.CreditAssesment;

/**
 * Credit Assesment Service
 * 
 * @author L0656968
 */
public interface CreditAssesmentService {

	CreditAssesment evaluate(Customer customer, Amount amount);

}
