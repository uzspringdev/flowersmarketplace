package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.*;
import com.example.flowers_marketplace.dto.MerchantDto;
import com.example.flowers_marketplace.mapper.MerchantMapper;
import com.example.flowers_marketplace.repository.MerchantRepository;
import com.example.flowers_marketplace.service.AddressService;
import com.example.flowers_marketplace.service.CardService;
import com.example.flowers_marketplace.service.MerchantService;
import com.example.flowers_marketplace.service.UserAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;
    private final AddressService addressService;
    private final CardService cardService;
    private final UserAccountService userAccountService;
    private final MerchantMapper merchantMapper = MerchantMapper.getInstance;

    public MerchantServiceImpl(MerchantRepository merchantRepository, AddressService addressService, CardService cardService, UserAccountService userAccountService) {
        this.merchantRepository = merchantRepository;
        this.addressService = addressService;
        this.cardService = cardService;
        this.userAccountService = userAccountService;
    }

    @Override
    public Merchant save(MerchantDto merchantDto) {
        Merchant merchant = merchantMapper.toEntity(merchantDto);
        UserAccount userAccount = userAccountService.save(merchant.getUserAccount());
        Address address = addressService.save(merchantDto.getAddress());
        List<Card> cards = cardService.saveAll(merchantDto.getCards());
        merchant.setUserAccount(userAccount);
        merchant.setAddress(address);
        merchant.setCards(cards);
        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant save(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public List<Merchant> findAll() {
        return merchantRepository.findAll();
    }

    @Override
    public Merchant findById(Long id) {
        return merchantRepository.findById(id).orElse(null);
    }

    @Override
    public Merchant update(Long id, MerchantDto merchantDto) {
        Optional<Merchant> optionalMerchant = merchantRepository.findById(id);
        if (optionalMerchant.isPresent()) {
            Merchant merchant = merchantMapper.updateMerchantFromDto(merchantDto, optionalMerchant.get());
            Address address = addressService.save(merchant.getAddress());
            List<Card> cards = cardService.saveAll(merchantMapper.toCardDtoList(merchant.getCards()));
            merchant.setAddress(address);
            merchant.setCards(cards);
            return merchant;
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        if (merchantRepository.existsById(id)) {
            merchantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Merchant> saveAll(List<Merchant> merchantList) {
        return merchantRepository.saveAll(merchantList);
    }
}
