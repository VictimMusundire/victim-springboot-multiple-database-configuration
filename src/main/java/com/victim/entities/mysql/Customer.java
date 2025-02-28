package com.victim.entities.mysql;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="customer")
public class Customer {

    @Id
    private Long customerId;

    private String customerName;

    private String address;
}
