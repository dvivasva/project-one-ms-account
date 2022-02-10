package com.microservice.account.inter;

import com.microservice.account.model.Account;
import reactor.core.publisher.Flux;

public interface IAccount {
    Flux<Account> getAccountsByICustomer(int customerId);
}
