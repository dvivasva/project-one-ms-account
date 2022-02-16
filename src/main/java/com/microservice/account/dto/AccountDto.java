package com.microservice.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

	private String id;
	private String type;
	private String number;
	private double availableBalance;
	private Date dateCreation;
	private String status;
	private String customerId;

}
