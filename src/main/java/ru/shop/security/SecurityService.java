package ru.shop.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

    public Optional<String> login(String username, String password) {
        return Optional.empty();
    }

    public Optional<UserDetails> authentication(String token) {
        return Optional.empty();
    }

    void logout(UserDetails user) {

    }

}
