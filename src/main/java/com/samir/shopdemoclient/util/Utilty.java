package com.samir.shopdemoclient.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class Utilty {

    public String sendGet(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    public String sendPost(String url, String data) {
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Type","application/json");
        headers.add("Accept","application/json");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url,new HttpEntity<>(data,headers), String.class);
        return response.getBody();
    }
}
