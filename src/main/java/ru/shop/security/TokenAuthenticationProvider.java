package ru.shop.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private SecurityService security;

    @Override
    protected void additionalAuthenticationChecks(final UserDetails d, final UsernamePasswordAuthenticationToken auth) {
        // Nothing to do
    }

    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) {
//        final Object token = authentication.getCredentials();
//        return Optional
//                .ofNullable(token)
//                .map(String::valueOf)
//                .flatMap(auth::findByToken)
//                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
        final String token = authentication.getCredentials().toString();
        return security.authentication(token).orElseThrow(
                () -> new RuntimeException("auth.provider.userNotFound.message")
        );
    }
}
