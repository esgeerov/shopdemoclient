package com.samir.shopdemoclient.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ResProduct {
    Long productId;
    String name;
    Integer price;
    String currency;
    RespCategory respCategory;

}
