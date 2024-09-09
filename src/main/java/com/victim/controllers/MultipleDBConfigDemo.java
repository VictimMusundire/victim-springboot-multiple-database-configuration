package com.victim.controllers;

import com.victim.dtos.mysql.CustomerDto;
import com.victim.dtos.postgres.CreditCardDto;
import com.victim.entities.mysql.Customer;
import com.victim.entities.postgres.CreditCard;
import com.victim.repositories.mysql.CustomerRepository;
import com.victim.repositories.postgres.CreditCardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demo/multiple-db")
public class MultipleDBConfigDemo {

    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final CreditCardRepository creditCardRepository;

    public MultipleDBConfigDemo(ModelMapper modelMapper, CustomerRepository customerRepository, CreditCardRepository creditCardRepository) {
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.creditCardRepository = creditCardRepository;
    }

    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable("id") Long customerId) {
        return modelMapper.map(customerRepository.findById(customerId), CustomerDto.class);
    }

    @PostMapping("/customers")
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        return modelMapper.map(customerRepository.save(customer), CustomerDto.class);
    }

    @GetMapping("/creditCard/{id}")
    public CreditCardDto getCreditCardInfo(@PathVariable("id") Long creditCardId) {
        return modelMapper.map(creditCardRepository.findById(creditCardId), CreditCardDto.class);
    }

    @PostMapping("/creditCard")
    public CreditCardDto saveCreditCardInfo(@RequestBody CreditCardDto creditCardDto) {
        CreditCard creditCard = modelMapper.map(creditCardDto, CreditCard.class);
        return modelMapper.map(creditCardRepository.save(creditCard), CreditCardDto.class);
    }

}
