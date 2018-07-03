package ru.shop.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class TokenAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    public TokenAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;

        String token = (String) tokenAuthentication.getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(token.split(":")[0]);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Unknown token");
        }

        tokenAuthentication.setAuthenticated(true);
        tokenAuthentication.setDetails(userDetails);

        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == TokenAuthentication.class;
    }

}
