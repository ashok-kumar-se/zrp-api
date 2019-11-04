package com.zrp.orderservice.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Accessors(chain = true)
public class OrderDto {

    private String webOrderId;

    private int numberOfSku;

    private String blackListTime;

}
