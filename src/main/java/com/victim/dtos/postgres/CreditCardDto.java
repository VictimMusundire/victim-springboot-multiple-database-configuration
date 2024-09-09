package com.victim.dtos.postgres;

import lombok.Data;

@Data
public class CreditCardDto {
    private Long id;
    private String name;
    private int expirationMonth;
    private int expirationYear;
}
