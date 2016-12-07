/**
 * 
 */
package com.cabrera.creditassesment.services.broker;

import com.cabrera.creditassesment.beans.Customer;

/**
 * Broker Credit Card Service Interface
 * 
 * @author L0656968
 */
public interface CreditCardService {

	Boolean hasCreditDebts(Customer customer);
}
