package com.cabrera.creditassesment.engine;

import io.reactivex.Observable;

import java.util.List;

import com.cabrera.creditassesment.beans.CreditCardMovement;

public interface CreditCardBroker {
	Observable<List<CreditCardMovement>> call();
}
