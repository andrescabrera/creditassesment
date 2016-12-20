package com.cabrera.creditassesment.beans;


public class Person extends Customer {

	private String name;
	private String surname;
	private String lastname;
	
	public Person(String name, String lastname) {
		super();
		this.name = name;
		this.lastname = lastname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [name=").append(name).append(", surname=")
				.append(surname).append(", lastname=").append(lastname)
				.append(", countAssesments()=").append(countAssesments())
				.append(", getRegistrationDate()=")
				.append(getRegistrationDate()).append("]");
		return builder.toString();
	}
	
}
