/**
 * 
 */
package com.cabrera.creditassesment.services.broker;

import java.util.List;
import java.util.concurrent.Callable;

import com.cabrera.creditassesment.beans.CreditCardMovement;

/**
 * Broker Credit Card Service Interface
 * 
 * @author L0656968
 */
public interface CreditCardService extends Callable<List<CreditCardMovement>> {

}
