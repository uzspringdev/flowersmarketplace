package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.domain.Card;
import com.example.flowers_marketplace.domain.Customer;
import com.example.flowers_marketplace.dto.CustomerDto;
import com.example.flowers_marketplace.mapper.CustomerMapper;
import com.example.flowers_marketplace.repository.CustomerRepository;
import com.example.flowers_marketplace.service.AddressService;
import com.example.flowers_marketplace.service.CardService;
import com.example.flowers_marketplace.service.CustomerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final BCryptPasswordEncoder encoder;
    private final CustomerMapper customerMapper = CustomerMapper.getInstance;
    private final CustomerRepository customerRepository;
    private final CardService cardService;
    private final AddressService addressService;


    public CustomerServiceImpl(BCryptPasswordEncoder encoder, CustomerRepository customerRepository, CardService cardService, AddressService addressService) {
        this.encoder = encoder;
        this.customerRepository = customerRepository;
        this.cardService = cardService;
        this.addressService = addressService;
    }

    @Override
    public Customer save(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customer.setAddress(addressService.save(customerDto.getAddress()));
        customer.setCards(cardService.saveAll(customerDto.getCards()));
        customer.setPassword(encoder.encode(customer.getPassword()));
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
            customer.setAddress(address);
            customer.setCards(cards);

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

/*
    Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            List<Card> oldCards = optionalCustomer.get().getCards();
            Customer customer = customerMapper.toEntity(customerDto);
            Address address = addressService.save(customerDto.getAddress());
            List<Card> cards = cardService.saveAll(customerDto.getCards());
            customer.setAddress(address);
            List<Card> totalCards = oldCards.addAll(cards) ? oldCards : cards;
            customer.setCards(totalCards);
            customer.setId(id);
            return customerRepository.save(customer);
        }
        return null;
*/
/*
 Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customerMapper.updateNotNullCustomerFromDto(customerDto, customer);
            customerMapper.updateCustomerFromDto(customerDto, customer);
            if (customer.getAddress() != null) {
                Address address = addressService.save(customerDto.getAddress());
                customer.setAddress(address);
            } else {
                AddressDto addressDto = customerMapper.toAddressDto(customer.getAddress());
                Address address = addressService.save(addressDto);
                customer.setAddress(address);
            }
            if (customer.getCards() != null) {
                List<Card> cards = cardService.saveAll(customerDto.getCards());
                customer.setCards(cards);
            }

            return customerRepository.save(customer);
        }
        return null;
*/
