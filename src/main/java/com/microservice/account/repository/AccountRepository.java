package com.microservice.account.repository;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.microservice.account.model.Account;
import reactor.core.publisher.Mono;

@Repository
public class AccountRepository {
	 private final static Logger LOGGER = LoggerFactory.getLogger(AccountRepository.class);

	 public Mono<List<Account>> accountList() {
		    List<Account> list = new ArrayList<>();
		  
		    list.add(new Account("ss","sdas","sdsa"));
		    list.add(new Account("ss","sdas","sdsa"));
		    list.add(new Account("ss","sdas","sdsa"));
		    list.add(new Account("ss","sdas","sdsa"));
		    LOGGER.info("inside method account list :)");
		    return Mono.just(list);
		}
	 
}
