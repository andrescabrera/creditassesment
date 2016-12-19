/**
 * 
 */
package com.cabrera.creditassesment.services.broker;

import io.reactivex.Observable;

import java.util.List;

import com.cabrera.creditassesment.beans.CreditCardMovement;

/**
 * Broker Credit Card Service Interface
 * 
 * @author L0656968
 */
public interface CreditCardService {
	Observable<List<CreditCardMovement>> call();
}
