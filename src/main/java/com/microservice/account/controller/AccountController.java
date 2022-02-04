package com.microservice.account.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.model.Account;
import com.microservice.account.service.AccountService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController{
	
	AccountService accountService;
	
	@GetMapping(path = "/list")
	public Mono<List<Account>>restAccountList() {
		return accountService.ACCOUNT_LIST();
	}

}
