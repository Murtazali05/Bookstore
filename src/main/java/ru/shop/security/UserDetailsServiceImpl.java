package ru.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.shop.persistense.entity.Role;
import ru.shop.persistense.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ru.shop.persistense.entity.User user = userRepository.findByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException("User " + email + " not found.");

        if (user.getRole() == null)
            throw new UsernameNotFoundException("User not authorized. No Roles assigned to user");

        return new User(user.getEmail(), user.getPassword(), buildUserAuthorities(user.getRole()));
    }

    private Set<GrantedAuthority> buildUserAuthorities(Role role) {
        Set<GrantedAuthority> auth = new HashSet<>();

        auth.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role.getName())));
        return auth;
    }

}
