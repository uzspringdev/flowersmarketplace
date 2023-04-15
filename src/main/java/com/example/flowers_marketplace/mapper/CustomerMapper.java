package com.example.flowers_marketplace.mapper;

import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.domain.Card;
import com.example.flowers_marketplace.domain.Customer;
import com.example.flowers_marketplace.dto.AddressDto;
import com.example.flowers_marketplace.dto.CardDto;
import com.example.flowers_marketplace.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerMapper getInstance = Mappers.getMapper(CustomerMapper.class);

    Customer toEntity(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);

    Address toAddressEntity(AddressDto addressDto);

    AddressDto toAddressDto(Address address);

    List<Card> toCardList(List<CardDto> cardDtos);

    List<CardDto> toCardDtoList(List<Card> cards);


    default Customer updateCustomerFromDto(CustomerDto customerDto, @MappingTarget Customer customer) {
        if (customerDto.getId() != null)
            customer.setId(customerDto.getId());
        if (customerDto.getUsername() != null)
            customer.setUsername(customerDto.getUsername());
        if (customerDto.getPassword() != null)
            customer.setPassword(customerDto.getPassword());
        if (customerDto.getFirstName() != null)
            customer.setFirstName(customerDto.getFirstName());
        if (customerDto.getLastName() != null)
            customer.setLastName(customerDto.getLastName());
        if (customerDto.getPhoneNumber() != null)
            customer.setPhoneNumber(customerDto.getPhoneNumber());
        if (customerDto.getEmail() != null)
            customer.setEmail(customerDto.getEmail());
        if (customerDto.getAddress() != null)
            customer.setAddress(toAddressEntity(customerDto.getAddress()));
        if (customerDto.getCards() != null) {
            List<Card> customerCards = customer.getCards();
            List<Card> newCards = toCardList(customerDto.getCards());
            customerCards.addAll(newCards);
            customer.setCards(customerCards);
        }
        customer.setCreatedAt(customerDto.getCreatedAt());
        customer.setUpdatedAt(customerDto.getUpdatedAt());

        return customer;
    }

}
