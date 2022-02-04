package com.microservice.account.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Account {
	
	
	private String id;
	private String typeAccount;
	private String numberAccount;
	/*private String keyAccount;
	private String availableBalanceAccount;
	private String dateCreationAccount;
	private String statusAccount;
	private Integer idBranchOffice;
	private Integer idClerkCreation;
	private Integer idCustomer;*/

	
	
	

}
