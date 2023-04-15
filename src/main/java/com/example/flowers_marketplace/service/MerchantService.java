package com.example.flowers_marketplace.service;

import com.example.flowers_marketplace.domain.Merchant;
import com.example.flowers_marketplace.dto.MerchantDto;

import java.util.List;

public interface MerchantService {
    Merchant save(MerchantDto merchantDto);

    Merchant save(Merchant merchant);

    List<Merchant> findAll();

    Merchant findById(Long id);

    Merchant update(Long id, MerchantDto merchantDto);

    Boolean delete(Long id);

    List<Merchant> saveAll(List<Merchant> merchantList);
}
