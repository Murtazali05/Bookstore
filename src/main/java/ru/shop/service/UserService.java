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
import ru.shop.security.UserDetailsImpl;
import ru.shop.service.dto.TokenDTO;
import ru.shop.service.dto.user.UserCreateDTO;
import ru.shop.service.dto.user.UserDTO;
import ru.shop.service.dto.user.UserLoginDTO;
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

    @Transactional(readOnly = true)
    public TokenDTO login(UserLoginDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());

        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Invalid credentials. Wrong password!");

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setAccessToken(
                tokenUtil.generateToken(
                        new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), user.getRole().getName().toUpperCase())
                )
        );
        tokenDTO.setTokenType(tokenUtil.getTokenPrefix());
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