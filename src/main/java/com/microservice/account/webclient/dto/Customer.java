package com.microservice.account.listenercustomer.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class Customer {
	private String id;
	private String name;
	private String lastname;
	private int dni;
	private String typcustomer;  // peronal o empresa
	private String profile; // vid  pyme

}
