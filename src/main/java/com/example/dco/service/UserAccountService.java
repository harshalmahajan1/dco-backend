package com.example.dco.service;

import com.example.dco.entity.UserAccount;
import com.example.dco.repository.UserAccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService {
    private final UserAccountRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserAccountService(UserAccountRepository repo) {
        this.repo = repo;
    }

    public UserAccount createUser(String username, String rawPassword) {
        UserAccount u = new UserAccount();
        u.setUsername(username);
        u.setPasswordHash(encoder.encode(rawPassword));
        return repo.save(u);
    }

    public Optional<UserAccount> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public boolean checkPassword(String raw, String encoded) {
        return encoder.matches(raw, encoded);
    }
}
