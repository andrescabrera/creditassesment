/**
 * 
 */
package com.cabrera.creditassesment.services;

import io.reactivex.Single;

import java.util.concurrent.ExecutionException;

import com.cabrera.creditassesment.beans.Amount;
import com.cabrera.creditassesment.beans.CreditAssesment;
import com.cabrera.creditassesment.beans.Customer;

/**
 * Credit Assesment Service
 * 
 * @author L0656968
 */
public interface CreditAssesmentService {

	Single<CreditAssesment> evaluate(Customer customer, Amount amount) throws InterruptedException, ExecutionException;

}
