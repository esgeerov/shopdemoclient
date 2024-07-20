package com.samir.shopdemoclient.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samir.shopdemoclient.dto.response.RespCategory;
import com.samir.shopdemoclient.dto.response.Response;
import com.samir.shopdemoclient.util.Utilty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@PropertySource(value = "config.properties")
public class CategoryService {
    @Value("${api.userId}")
    private Long apiUserid;
    @Value("${api.token}")
    private String apiToken;
    @Value("${api.url}")
    private String apiUrl;

    private final Utilty utilty;
    public Response<List<RespCategory>> getCategoryList() throws Exception {
        ObjectMapper objectMapper=new ObjectMapper();
       String result= utilty.sendGet(apiUrl+"category/getCategoryList");
       Response<List<RespCategory>> response=objectMapper.readValue(result,Response.class);
        return response;
    }
}
