package com.microservice.account.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Account {
	
	
	@Id
	private String id;
	private String typeAccount;
	private String numberAccount;
	private int keyAccount;
	private double availableBalanceAccount;
	private String dateCreationAccount;
	private String statusAccount;
	private int idClerkCreation;
	private int idCustomer;
	

}
