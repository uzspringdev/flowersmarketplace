package com.example.flowers_marketplace.mapper;

import com.example.flowers_marketplace.domain.*;
import com.example.flowers_marketplace.dto.AddressDto;
import com.example.flowers_marketplace.dto.CardDto;
import com.example.flowers_marketplace.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Mapper
public interface CustomerMapper {

    CustomerMapper getInstance = Mappers.getMapper(CustomerMapper.class);

    //Customer toEntity(CustomerDto customerDto);


    Address toAddressEntity(AddressDto addressDto);

    AddressDto toAddressDto(Address address);

    List<Card> toCardList(List<CardDto> cardDtos);

    List<CardDto> toCardDtoList(List<Card> cards);

    default Customer toEntity(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }
        Customer customer = new Customer();
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(customerDto.getUsername());
        userAccount.setPassword(customerDto.getPassword());
        userAccount.setUserType(customerDto.getUserType());
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setEmail(customerDto.getEmail());
        customer.setAddress(toAddressEntity(customerDto.getAddress()));
        customer.setCards(toCardList(customerDto.getCards()));
        Set<Role> set = customerDto.getRoles();
        if (set != null) {
            customer.setRoles(new LinkedHashSet<>(set));
        }
        customer.setLangKey(customerDto.getLangKey());
        customer.setCreatedAt(customerDto.getCreatedAt());
        customer.setUpdatedAt(customerDto.getUpdatedAt());
        customer.setUserAccount(userAccount);

        return customer;
    }

    default Customer updateCustomerFromDto(CustomerDto customerDto, @MappingTarget Customer customer) {
        UserAccount userAccount = new UserAccount();
        if (customerDto.getId() != null)
            customer.setId(customerDto.getId());
        if (customerDto.getUsername() != null)
            userAccount.setUsername(customerDto.getUsername());
        if (customerDto.getPassword() != null)
            userAccount.setPassword(customerDto.getPassword());
        if (customerDto.getUserType() != null)
            userAccount.setUserType(customerDto.getUserType());
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
        userAccount.setId(customer.getId());
        customer.setCreatedAt(customerDto.getCreatedAt());
        customer.setUpdatedAt(customerDto.getUpdatedAt());
        customer.setUserAccount(userAccount);

        return customer;
    }

    CustomerDto toDto(Customer customer);
}
