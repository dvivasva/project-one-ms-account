package com.microservice.account.webclient.dto;

import lombok.*;

@Data
public class Customer {
	private String id;
	private String name;
	private String lastname;
	private int dni;
	private String typeCustomer;
	private String profile;

}
