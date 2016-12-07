/**
 * 
 */
package com.cabrera.creditassesment.beans;

/**
 * Business Customer
 * 
 * @author L0656968
 */
public class Business extends Customer {

	private String name;
	private String cuit;

	public Business(String name, String cuit) {
		super();
		this.name = name;
		this.cuit = cuit;
	}

	public String getName() {
		return name;
	}

	public String getCuit() {
		return cuit;
	}
	
}
