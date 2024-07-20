package com.samir.shopdemoclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.samir.shopdemoclient.dto.request.ReqLogin;
import com.samir.shopdemoclient.dto.response.RespUser;
import com.samir.shopdemoclient.dto.response.Response;
import com.samir.shopdemoclient.util.Utilty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PropertySource(value = "config.properties")
public class LoginService {
    @Value("${api.url}")
    private String apiUrl;
    private final Utilty utilty;


    public Response<RespUser> login(ReqLogin reqLogin)  {
        ObjectMapper objectMapper=new ObjectMapper();
        Response<RespUser> response=new Response<>();
        try {
            String data= objectMapper.writeValueAsString(reqLogin);
           String result= utilty.sendPost(apiUrl+"user/login",data);
          response= objectMapper.readValue(result, new TypeReference<>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }


        return  response;
    }
}
