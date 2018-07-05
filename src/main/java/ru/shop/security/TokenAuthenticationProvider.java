package ru.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private SecurityService securityService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) {
        final String token = authentication.getCredentials().toString();

        return securityService.findByToken(token).orElseThrow(
                () -> new RuntimeException("auth.provider.userNotFoundByToken.message")
        );
    }
}
