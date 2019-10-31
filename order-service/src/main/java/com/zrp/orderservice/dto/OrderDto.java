package com.zrp.orderservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderDto {

    private String webOrderId;

    private int numberOfSku;

    private String blackListTime;

}
