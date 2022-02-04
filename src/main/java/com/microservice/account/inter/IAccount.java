package com.microservice.account.inter;

import java.util.List;

import com.microservice.account.model.Account;

import reactor.core.publisher.Mono;

public interface IAccount {

	Mono<List<Account>> ACCOUNT_LIST();
	
}
