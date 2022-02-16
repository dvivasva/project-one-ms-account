package com.microservice.account.webclient;

import com.microservice.account.webclient.dto.Customer;
import com.microservice.account.utils.UriAccess;
import com.microservice.account.utils.UriBase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

public class CustomerWebClient {

    WebClient client = WebClient.builder()
            .baseUrl(UriBase.LOCALHOST_8092)
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", UriBase.LOCALHOST_8092))
            .build();

    public Mono<Customer> getCustomerMono(String id) {
        return client.get()
                .uri( UriBase.LOCALHOST_8092 + UriAccess.CUSTOMER +id)
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
