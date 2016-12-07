package com.cabrera.creditassesment.engine;

import java.util.List;
import java.util.concurrent.Callable;

import com.cabrera.creditassesment.beans.CreditCardMovement;

public interface CreditCardBroker extends Callable<List<List<CreditCardMovement>>> {
	
}
