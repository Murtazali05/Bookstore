package ru.shop.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImpl implements UserDetails {
    private Integer id;
    private String email;
    private String passwordHash;
    private Set<GrantedAuthority> authorities;

    public UserDetailsImpl(Integer id, String email, String passwordHash, String role) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.authorities = buildUserAuthorities(role);
    }

    public Integer getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    String getRole() {
        List<String> roles = new ArrayList<>();

        for (GrantedAuthority grantedAuthority : authorities) {
            roles.add(grantedAuthority.getAuthority());
        }

        return roles.get(0);
    }

    private Set<GrantedAuthority> buildUserAuthorities(String role) {
        Set<GrantedAuthority> auth = new HashSet<>();

        auth.add(new SimpleGrantedAuthority(role));

        return auth;
    }

}
