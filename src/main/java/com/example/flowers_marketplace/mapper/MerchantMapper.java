package com.example.flowers_marketplace.mapper;


import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.domain.Card;
import com.example.flowers_marketplace.domain.Merchant;
import com.example.flowers_marketplace.domain.Role;
import com.example.flowers_marketplace.dto.AddressDto;
import com.example.flowers_marketplace.dto.CardDto;
import com.example.flowers_marketplace.dto.MerchantDto;
import com.example.flowers_marketplace.model.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Mapper
public interface MerchantMapper {

    MerchantMapper getInstance = Mappers.getMapper(MerchantMapper.class);

    Merchant toEntity(MerchantDto merchantDto);

    MerchantDto toDto(Merchant merchant);

    Address toAddressEntity(AddressDto addressDto);

    AddressDto toAddressDto(Address address);

    List<Card> toCardList(List<CardDto> cardDtos);

    List<CardDto> toCardDtoList(List<Card> cards);

    //void updateMerchantFromDto(MerchantDto merchantDto, @MappingTarget Merchant merchant);

    default Merchant updateMerchantFromDto(MerchantDto merchantDto, @MappingTarget Merchant merchant) {
        if (merchantDto == null) {
            return null;
        }
        if (merchantDto.getFirstName() != null)
            merchant.setFirstName(merchantDto.getFirstName());
        if (merchantDto.getLastName() != null)
            merchant.setLastName(merchantDto.getLastName());
        if (merchantDto.getPhoneNumber() != null)
            merchant.setPhoneNumber(merchantDto.getPhoneNumber());
        if (merchantDto.getEmail() != null)
            merchant.setEmail(merchantDto.getEmail());
        if (merchantDto.getRoles() != null) {
            Set<Role> merchantRoles = merchant.getRoles();
            Set<Role> newRoles = merchantDto.getRoles();
            merchantRoles.addAll(newRoles);
            merchant.setRoles(merchantRoles);
        }
        if (merchantDto.getAddress() != null)
            merchant.setAddress(toAddressEntity(merchantDto.getAddress()));
        if (merchantDto.getCards() != null) {
            List<Card> merchantCards = merchant.getCards();
            List<Card> newCards = toCardList(merchantDto.getCards());
            merchantCards.addAll(newCards);
        }
        if (merchantDto.getRate() != null) {
            merchant.setRate(merchantDto.getRate());
        }
        merchant.setCreatedAt(merchantDto.getCreatedAt());
        merchant.setUpdatedAt(merchantDto.getUpdatedAt());
        return merchant;
    }
}
