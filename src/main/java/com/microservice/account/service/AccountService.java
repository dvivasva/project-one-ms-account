package com.microservice.account.service;


import com.microservice.account.inter.IAccount;
import com.microservice.account.listenercustomer.CustomerWebClient;
import com.microservice.account.listenercustomer.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.microservice.account.model.Account;
import com.microservice.account.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.management.PersistentMBean;

@Service
@RequiredArgsConstructor
public class AccountService{

	 private  final AccountRepository accountRepository;
	 private final ReactiveMongoTemplate reactiveMongoTemplate;
	 private final static Logger logger= LoggerFactory.getLogger(AccountService.class);

     // crud
	  public Flux<Account> getAllAccount(){



	    return accountRepository.findAll();
	  }
	  public Mono<Account> getAccountById(String id){
	    return  accountRepository.findById(id);
	  }
	  public Mono<Account> createAccount(Account account){
		  CustomerWebClient customerWebClient= new CustomerWebClient();
		  Mono<Customer>customerMono = customerWebClient.getCustomerMono("60323411");
		  Mono<Account> accountMono = null;
		               accountMono.map(document->{
						   //verify type customer

						   //verify number account
						   return  document;
					   });

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
	              bean.setCustomerId(account.getCustomerId());
	              return accountRepository.save(bean);
	            });
	  }
	  public Mono<Account> deleteAccount(String id){
	    return accountRepository.findById(id)
	            .flatMap(existsAccount -> accountRepository.delete(existsAccount)
	                    .then(Mono.just(existsAccount)));
	  }



	  public Flux<Account> findAllAccountsByCustomerId(String customerId) {
		  return this.accountRepository.findAll()
				  .filter(document->document.getCustomerId().equals(customerId));
	  }
	public Mono<Customer> getCustomerById(String id){
		CustomerWebClient customerWebClient= new CustomerWebClient();
		return customerWebClient.getCustomerMono(id);
	}

}
