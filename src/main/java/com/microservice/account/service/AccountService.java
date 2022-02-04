package com.microservice.account.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.account.inter.IAccount;
import com.microservice.account.model.Account;
import com.microservice.account.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccount {
	
	AccountRepository accountRepository;

	@Override
	public Mono<List<Account>> ACCOUNT_LIST() {
		return accountRepository.accountList();
	}

}
