package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.Address;
import com.example.flowers_marketplace.domain.Card;
import com.example.flowers_marketplace.domain.Merchant;
import com.example.flowers_marketplace.domain.Role;
import com.example.flowers_marketplace.dto.MerchantDto;
import com.example.flowers_marketplace.mapper.MerchantMapper;
import com.example.flowers_marketplace.repository.MerchantRepository;
import com.example.flowers_marketplace.service.AddressService;
import com.example.flowers_marketplace.service.CardService;
import com.example.flowers_marketplace.service.MerchantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;
    private final AddressService addressService;
    private final CardService cardService;
    private final MerchantMapper merchantMapper = MerchantMapper.getInstance;

    public MerchantServiceImpl(MerchantRepository merchantRepository, AddressService addressService, CardService cardService) {
        this.merchantRepository = merchantRepository;
        this.addressService = addressService;
        this.cardService = cardService;
    }

    @Override
    public Merchant save(MerchantDto merchantDto) {
        Merchant merchant = merchantMapper.toEntity(merchantDto);
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
            Set<Role> roles = merchant.getRoles();
            merchant.setAddress(address);
            merchant.setCards(cards);
            merchant.setRoles(roles);
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
