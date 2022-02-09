package com.microservice.account.listenercustomer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.microservice.account.listenercustomer.dto.Customer;
import com.microservice.account.utils.JSONUtils;
import com.microservice.account.utils.UriAccess;
import com.microservice.account.utils.UriBase;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CustomerClient {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build();


    public Customer getCustomerById(String id){
        String url= UriBase.baseUrl+ UriAccess.GET_CUSTOMER_BY_ID +id;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        response.thenAccept(System.out::println);
        Customer customer = null;
        try {
            customer= JSONUtils.convertFromJsonToObject(response.get().body(), Customer.class);
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return customer;
    }
}
