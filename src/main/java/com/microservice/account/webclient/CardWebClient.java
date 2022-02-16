package com.microservice.account.webclient;

import com.microservice.account.utils.UriBase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

public class CardWebClient {
    WebClient client = WebClient.builder()
            .baseUrl(UriBase.LOCALHOST_8093)
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", UriBase.LOCALHOST_8093))
            .build();
}
