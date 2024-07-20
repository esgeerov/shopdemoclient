package com.samir.shopdemoclient.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class RespStatus {

    Integer code;
    String message;
}
