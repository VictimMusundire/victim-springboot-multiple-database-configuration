package com.victim.dtos.mysql;

import lombok.Data;

@Data
public class CustomerDto {

    private Long customerId;
    private String customerName;
    private String address;

}
