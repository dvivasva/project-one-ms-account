package com.microservice.account.controller;


import com.microservice.account.dto.AccountDto;
import com.microservice.account.service.AccountService;
import com.microservice.account.webclient.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController{
	
private final AccountService accountService;
	
	@GetMapping
	public Flux<AccountDto>getAllAccount() {
		return accountService.getAllAccount();
	}

	@GetMapping("/{id}")
	public Mono<AccountDto> getAccountById(@PathVariable String id){
		return accountService.getAccountById(id);
	}

	@PostMapping
	public Mono<AccountDto> createAccount(@RequestBody Mono<AccountDto> accountDtoMono) {
		return accountService.createAccount(accountDtoMono);
	}

	@PutMapping("/{id}")
	public Mono<AccountDto> updateAccount(@RequestBody Mono<AccountDto> accountDtoMono, @PathVariable String id){
		return accountService.updateAccount(accountDtoMono,id);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteAccount(@PathVariable String id){
		return accountService.deleteAccount(id);
	}

	@GetMapping("/listByCustomer/{id}")
	public Flux<AccountDto> getAccountsByCustomerId(@PathVariable("id") String id){
		return accountService.findAllAccountsByCustomerId(id);
	}


	//only test
	@GetMapping("/customer/{id}")
	public Mono<Customer> getCustomerById(@PathVariable("id") String id) {
		return accountService.getCustomerById(id);
	}
}
