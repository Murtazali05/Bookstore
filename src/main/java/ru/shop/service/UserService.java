package ru.shop.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.persistense.entity.User;
import ru.shop.persistense.repository.UserRepository;
import ru.shop.security.TokenUtil;
import ru.shop.service.dto.TokenDTO;
import ru.shop.service.dto.user.UserCreateDTO;
import ru.shop.service.dto.user.UserDTO;
import ru.shop.service.mapper.user.UserCreateMapper;
import ru.shop.service.mapper.user.UserMapper;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserCreateMapper userCreateMapper;

    private BCryptPasswordEncoder passwordEncoder;
    private TokenUtil tokenUtil;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setUserCreateMapper(UserCreateMapper userCreateMapper) {
        this.userCreateMapper = userCreateMapper;
    }

    @Autowired
    public void setTokenUtil(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public UserDTO getUser(Integer id) throws NotFoundException {
        if (!userRepository.existsById(id))
            throw new NotFoundException("The user not found!");

        return userMapper.toDTO(userRepository.getOne(id));
    }

    @Transactional
    public TokenDTO login(String email, String password) throws NotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null)
            throw new NotFoundException("The user not found by email=" + email);

//        if (!passwordEncoder.matches(password, user.getPassword()))
//            throw new BadCredentialsException("Invalid credentials. Wrong password!");

        if (!password.equals(user.getPassword()))
            throw new BadCredentialsException("Invalid credentials. Wrong password!");

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(
                tokenUtil.generateToken(
                        org.springframework.security.core.userdetails.User.builder()
                                .username(user.getEmail())
                                .password(user.getPassword())
                                .roles(user.getRole().getName().toUpperCase())
                                .build()
                )
        );
        tokenDTO.setUserDTO(userMapper.toDTO(user));

        return tokenDTO;
    }

    @Transactional
    public UserDTO create(UserCreateDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userRepository.save(userCreateMapper.toEntity(userDTO));
        return userMapper.toDTO(user);
    }

}