package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.domain.Card;
import com.example.flowers_marketplace.domain.Customer;
import com.example.flowers_marketplace.domain.UserAccount;
import com.example.flowers_marketplace.dto.CustomerDto;
import com.example.flowers_marketplace.mapper.CustomerMapper;
import com.example.flowers_marketplace.repository.CustomerRepository;
import com.example.flowers_marketplace.service.AddressService;
import com.example.flowers_marketplace.service.CardService;
import com.example.flowers_marketplace.service.CustomerService;
import com.example.flowers_marketplace.service.UserAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper = CustomerMapper.getInstance;
    private final CustomerRepository customerRepository;
    private final CardService cardService;
    private final AddressService addressService;
    private final UserAccountService userAccountService;


    public CustomerServiceImpl(CustomerRepository customerRepository, CardService cardService, AddressService addressService, UserAccountService userAccountService) {
        this.customerRepository = customerRepository;
        this.cardService = cardService;
        this.addressService = addressService;
        this.userAccountService = userAccountService;
    }

    @Override
    public Customer save(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customer.setAddress(addressService.save(customerDto.getAddress()));
        customer.setCards(cardService.saveAll(customerDto.getCards()));
        customer.setUserAccount(userAccountService.save(customer.getUserAccount()));
        return customerRepository.save(customer);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Long id, CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = customerMapper.updateCustomerFromDto(customerDto, optionalCustomer.get());
            Address address = addressService.save(customer.getAddress());
            List<Card> cards = cardService.saveAll(customerMapper.toCardDtoList(customer.getCards()));
            UserAccount userAccount = userAccountService.save(customer.getUserAccount());
            customer.setAddress(address);
            customer.setCards(cards);
            customer.setUserAccount(userAccount);

            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}