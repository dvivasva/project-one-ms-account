package com.microservice.account.listenercustomer;

import com.microservice.account.listenercustomer.dto.Customer;
import com.microservice.account.model.Account;
import com.microservice.account.utils.UriAccess;
import com.microservice.account.utils.UriBase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class CustomerWebClient {

    WebClient client = WebClient.builder()
            .baseUrl(UriBase.baseUrl)
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", UriBase.baseUrl))
            .build();

    public Mono<Customer> getCustomerMono(String id) {
        return client.get()
                .uri( UriBase.baseUrl+ UriAccess.GET_CUSTOMER_BY_ID+id)
                .accept(MediaType.APPLICATION_NDJSON)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(Customer.class);
                    }
                else {
                    // Turn to error
                    return response.createException().flatMap(Mono::error);
                }
        });
    }
    /*
    public Mono<Boolean> isEnterprise(Mono<Customer> customerMono){

        Mono<Boolean> isUserAllowed = Mono.just()
                .map(UserEntityUtils::convertEntityToModel)
                .filter(UserModelUtils::isUserAdmin)
                .switchIfEmpty(Mono.defer(() -> false))
                .flatmap(userModel -> true);

        return isUserAllowed;
    }*/

}
