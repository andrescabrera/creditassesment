/**
 * 
 */
package com.cabrera.creditassesment.engine;

import io.reactivex.Observable;

import java.util.concurrent.ExecutionException;

import com.cabrera.creditassesment.beans.Amount;
import com.cabrera.creditassesment.beans.Customer;
import com.cabrera.creditassesment.beans.CreditAssesment;

/**
 * Credit Assesment Engine
 * 
 * @author L0656968
 */
public interface CreditAssesmentEngine {

	Observable<CreditAssesment> evaluate(Customer customer, Amount amount) throws InterruptedException, ExecutionException;

}
