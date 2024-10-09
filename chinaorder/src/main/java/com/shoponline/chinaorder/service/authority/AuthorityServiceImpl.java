package com.shoponline.chinaorder.service.authority;


import com.shoponline.chinaorder.dao.AuthorityRepository;
import com.shoponline.chinaorder.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public List<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority createAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public Authority findAuthorityByUsername(String username) {
        return authorityRepository.findByUsername(username);
    }

    @Override
    public void deleteAuthority(String username) {
        authorityRepository.deleteByUsername(username);
    }
}
