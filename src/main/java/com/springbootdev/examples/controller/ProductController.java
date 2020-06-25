package com.springbootdev.examples.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.springbootdev.examples.model.Product;
import com.springbootdev.examples.service.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ProductController {

    @RolesAllowed("ROLE_USER")
    @GetMapping("/products")
    public List<Product> getProducts() {
        return List.of(
                Product.builder().id(1L).name("product 1").build(),
                Product.builder().id(2L).name("product 2").build()
        );
    }


    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/products")
    public Product addProduct(Principal principal) {
        System.out.println("====test" + principal.getName());
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return Product.builder()
                .id(4L)
                .name("new product")
                .build();
    }

    @PostMapping("/users")
    public void create() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.add("PRIVATE-TOKEN", "xyz");
//
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("client_id","admin-cli");
//        map.add("client_secret","0b22aead-f457-4a9e-9ff8-e0fef3755d0c");
//        map.add("grant_type","client_credentials");
//
//        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
//
//        ResponseEntity<TokenResponse> response =
//                restTemplate.exchange("http://localhost:8080/auth/realms/master/protocol/openid-connect/token",
//                        HttpMethod.POST,
//                        entity,
//                        TokenResponse.class);
//
//        System.out.println(Objects.requireNonNull(response.getBody()).getAccessToken());


        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("PRIVATE-TOKEN", "xyz");

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonMessageConverter = new MappingJackson2HttpMessageConverter();
        jsonMessageConverter.setObjectMapper(getObjectMapper());
        messageConverters.add(jsonMessageConverter);
        messageConverters.add(new FormHttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", "admin-cli");
        map.add("client_secret", "0b22aead-f457-4a9e-9ff8-e0fef3755d0c");
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<TokenResponse> response =
                restTemplate.exchange("http://localhost:8080/auth/realms/master/protocol/openid-connect/token",
                        HttpMethod.POST,
                        entity,
                        TokenResponse.class);

        System.out.println(Objects.requireNonNull(response.getBody()).getAccessToken());
    }


    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
