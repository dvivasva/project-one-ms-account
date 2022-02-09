package com.microservice.account.listenercustomer;

import com.microservice.account.listenercustomer.dto.Customer;
import com.microservice.account.utils.UriAccess;
import com.microservice.account.utils.UriBase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;



@RequiredArgsConstructor
public class CustomerWebClient {

    private final WebClient webClient;

    public Mono<Customer> getCustomerMono(String id) {
        return this.webClient.get().uri(UriAccess.GET_CUSTOMER_BY_ID, id)
                .retrieve().bodyToMono(Customer.class);
    }

    /*
    public Customer getCustomerById(String id) {
        return this.webClient.get().uri(UriAccess.GET_CUSTOMER_BY_ID, id)
                .retrieve()
                .bodyToMono(Customer.class).block();
    }*/





}
