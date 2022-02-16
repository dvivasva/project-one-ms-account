package com.microservice.account.service;


import com.microservice.account.dto.AccountDto;
import com.microservice.account.repository.AccountRepository;
import com.microservice.account.utils.AccountUtil;
import com.microservice.account.webclient.CustomerWebClient;
import com.microservice.account.webclient.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountService{

	 private  final AccountRepository accountRepository;
	 private final ReactiveMongoTemplate reactiveMongoTemplate;
	 private final static Logger logger= LoggerFactory.getLogger(AccountService.class);

	public Flux<AccountDto> getAllAccount(){
		return accountRepository.findAll().map(AccountUtil::entityToDto);
	}

	public Mono<AccountDto> getAccountById(String id){
		return accountRepository.findById(id).map(AccountUtil::entityToDto);
	}


	  public Mono<AccountDto> createAccount(Mono<AccountDto> accountDtoMono){
		  CustomerWebClient customerWebClient= new CustomerWebClient();

		  accountDtoMono.map(p->{
			  var customerMono = customerWebClient.getCustomerMono(p.getCustomerId());
			  customerMono.switchIfEmpty(Mono.error(new ClassNotFoundException("no exist account")));

			  //Is Person
				  //verify qty number account
				  //findAllAccountsByCustomerId // ya no puede crear cuenta


			  //Is Enterprise
			    //cuenta corriente
			  return p;
		  });
		  return  accountDtoMono.map(AccountUtil::dtoToEntity)
				  .flatMap(accountRepository::insert)
				  .map(AccountUtil::entityToDto);

	  }


	public Mono<AccountDto> updateAccount(Mono<AccountDto> accountDtoMono, String id){
		return accountRepository.findById(id)
				.flatMap(p->accountDtoMono.map(AccountUtil::dtoToEntity)
						.doOnNext(e->e.setId(id)))
				.flatMap(reactiveMongoTemplate::save)
				.map(AccountUtil::entityToDto);

	}

	public Mono<Void> deleteAccount(String id){
		return accountRepository.deleteById(id);
	}


	public Flux<AccountDto> findAllAccountsByCustomerId(String customerId) {
		logger.info("get accounts by  customer");
		  return this.accountRepository.findAll()
				  .filter(document->document.getCustomerId().equals(customerId)).map(AccountUtil::entityToDto);

	}


	//test
	public Mono<Customer> getCustomerById(String id){
		CustomerWebClient customerWebClient= new CustomerWebClient();
		return customerWebClient.getCustomerMono(id);
	}

}
