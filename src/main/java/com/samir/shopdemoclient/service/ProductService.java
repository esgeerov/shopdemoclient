package com.samir.shopdemoclient.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samir.shopdemoclient.dto.request.ReqToken;
import com.samir.shopdemoclient.dto.response.ResProduct;
import com.samir.shopdemoclient.dto.response.Response;
import com.samir.shopdemoclient.util.Utilty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@PropertySource("classpath:config.properties")


@RequiredArgsConstructor
public class ProductService {
    @Value("${api.url}")
    private String apiUrl;
    @Value("${api.token}")
    private String apiToken;
    @Value("${api.userId}")
    private Long apiUserId;

    private final Utilty utilty;

    public Response<List<ResProduct>> getProductList(Long categoryId) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
//        ReqToken reqToken = new ReqToken();
//        reqToken.setToken(apiToken);
//        reqToken.setUserId(apiUserId);
//
//        String data = objectMapper.writeValueAsString(reqToken);
        String result = utilty.sendGet(apiUrl + "product/getProductListByCategoryId/"+categoryId);
        Response<List<ResProduct>> response = objectMapper.readValue(result, Response.class);
        return response;
    }

}
