package com.microservice.account.service;


import org.springframework.stereotype.Service;
import com.microservice.account.model.Account;
import com.microservice.account.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountService {
	
	 private  final AccountRepository accountRepository;

	  public Flux<Account> getAllAccount(){
	    return accountRepository.findAll();
	  }
	  public Mono<Account> getAccountById(String id){
	    return  accountRepository.findById(id);
	  }
	  public Mono<Account> createAccount(Account account){
	    return accountRepository.save(account);
	  }
	  public Mono<Account> updateAccount(String id,  Account account){
	    return accountRepository.findById(id)
	            .flatMap(bean -> {
	              bean.setTypeAccount(account.getTypeAccount());
	              bean.setNumberAccount(account.getNumberAccount());
	              bean.setKeyAccount(account.getKeyAccount());
	              bean.setAvailableBalanceAccount(account.getAvailableBalanceAccount());
	              bean.setDateCreationAccount(account.getDateCreationAccount());
	              bean.setStatusAccount(account.getStatusAccount());
	              bean.setIdClerkCreation(account.getIdClerkCreation());
	              bean.setIdCustomer(account.getIdCustomer());
	              return accountRepository.save(bean);
	            });
	  }
	  public Mono<Account> deleteAccount(String id){
	    return accountRepository.findById(id)
	            .flatMap(existsAccount -> accountRepository.delete(existsAccount)
	                    .then(Mono.just(existsAccount)));
	  }


}
