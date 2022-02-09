package com.microservice.account.service;


import com.microservice.account.listenercustomer.CustomerClient;
import com.microservice.account.listenercustomer.CustomerWebClient;
import com.microservice.account.listenercustomer.dto.Customer;
import com.microservice.account.utils.UriBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.stereotype.Service;
import com.microservice.account.model.Account;
import com.microservice.account.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountService {

	 private  final AccountRepository accountRepository;
	private final static Logger logger= LoggerFactory.getLogger(AccountService.class);


	  public Flux<Account> getAllAccount(){
		  WebClient webClient = WebClient.create();

		  //webclient
		  var customerWebClient=new CustomerWebClient(webClient);
		  var beanMono= customerWebClient.getCustomerMono("60323411");
		  logger.info("this is bean Mono: " + beanMono);

		  //httpclient
		  var customerClient=new CustomerClient();
		  var beanObject=customerClient.getCustomerById("60323411");
		  logger.info("this is bean Object " + beanObject.toString());

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
