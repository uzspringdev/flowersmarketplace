package com.example.flowers_marketplace.service;

import com.example.flowers_marketplace.domain.UserAccount;
import com.example.flowers_marketplace.dto.UserAccountDto;

import java.util.List;

public interface UserAccountService {


    UserAccount save(UserAccountDto userAccountDto);

    UserAccount save(UserAccount userAccount);

    UserAccount findById(Long id);

    List<UserAccount> findAll();

    UserAccount update(Long id, UserAccountDto userAccountDto);

    Boolean delete(Long id);
}
