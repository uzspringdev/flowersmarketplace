package com.example.flowers_marketplace.service.impl;

import com.example.flowers_marketplace.domain.UserAccount;
import com.example.flowers_marketplace.dto.UserAccountDto;
import com.example.flowers_marketplace.mapper.UserAccountMapper;
import com.example.flowers_marketplace.repository.UserAccountRepository;
import com.example.flowers_marketplace.service.UserAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper = UserAccountMapper.getInstance;

    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount save(UserAccountDto userAccountDto) {
        UserAccount userAccount = userAccountMapper.toEntity(userAccountDto);
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount findById(Long id) {
        return userAccountRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserAccount> findAll() {
        return userAccountRepository.findAll();
    }

    @Override
    public UserAccount update(Long id, UserAccountDto userAccountDto) {

        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
