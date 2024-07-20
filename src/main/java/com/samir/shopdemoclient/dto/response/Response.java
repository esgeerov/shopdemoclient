package com.samir.shopdemoclient.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Response<T> {
    @JsonProperty(value = "response")
    T t;
    RespStatus respStatus;

}
