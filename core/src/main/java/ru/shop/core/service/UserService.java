package ru.shop.core.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.core.exception.AlreadyExistsException;
import ru.shop.core.exception.OldPasswordIncorrectException;
import ru.shop.core.exception.NotConfirmedException;
import ru.shop.core.persistense.entity.User;
import ru.shop.core.persistense.repository.RoleRepository;
import ru.shop.core.persistense.repository.UserRepository;
import ru.shop.core.security.TokenUtil;
import ru.shop.core.security.UserDetailsImpl;
import ru.shop.core.service.dto.TokenDTO;
import ru.shop.core.service.dto.user.UserCreateDTO;
import ru.shop.core.service.dto.user.UserDTO;
import ru.shop.core.service.dto.user.UserLoginDTO;
import ru.shop.core.service.dto.user.UserUpdateDTO;
import ru.shop.core.service.enumeration.RoleEnum;
import ru.shop.core.service.mapper.user.UserCreateMapper;
import ru.shop.core.service.mapper.user.UserMapper;
import ru.shop.core.service.mapper.user.UserUpdateMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserCreateMapper userCreateMapper;
    private UserUpdateMapper userUpdateMapper;

    private BCryptPasswordEncoder passwordEncoder;
    private TokenUtil tokenUtil;
    private EmailService emailService;
    private RoleRepository roleRepository;

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
    public void setUserUpdateMapper(UserUpdateMapper userUpdateMapper) {
        this.userUpdateMapper = userUpdateMapper;
    }

    @Autowired
    public void setTokenUtil(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public UserDTO get(int id) throws NotFoundException {
        return userMapper.toDTO(
                userRepository
                        .findById(id)
                        .orElseThrow(() -> new NotFoundException("The user not found!"))
        );
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
        return userMapper.toDTOs(
                userRepository.findAll()
        );
    }

    @Transactional(readOnly = true)
    public TokenDTO login(UserLoginDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());

        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Invalid credentials. Wrong password!");

        if (!user.getConfirmation())
            throw new NotConfirmedException("User not confirmation! Go to the mail and confirm the account");

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setAccessToken(
                tokenUtil.generateToken(
                        new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), user.getRole().getName().toUpperCase())
                )
        );
        tokenDTO.setTokenType(tokenUtil.getTokenPrefix());
        tokenDTO.setUser(userMapper.toDTO(user));

        return tokenDTO;
    }

    @Transactional
    public UserDTO create(UserCreateDTO userCreateDTO, HttpServletRequest request) {
        userCreateDTO.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        User user = userCreateMapper.toEntity(userCreateDTO);
        user.setConfirmation(false);
        UUID uuid = UUID.randomUUID();
        user.setConfirmCode(uuid.toString());
        emailService.sendEmail(user.getEmail(), user.getConfirmCode(), request.getRequestURL().toString());

        user.setRole(roleRepository.getOne(3));

        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    public TokenDTO confirm(String token) {
        User user = userRepository.findByConfirmCode(token);

        if (user == null || user.getConfirmation())
            throw new IllegalArgumentException("Token is not valid!");

        if (userRepository.existsByEmailAndConfirmation(user.getEmail(), true))
            throw new AlreadyExistsException("User with such email already exists and is confirmed!");

        user.setConfirmation(true);
        userRepository.save(user);

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setAccessToken(
                tokenUtil.generateToken(
                        new UserDetailsImpl(user.getId(), user.getEmail(), user.getPassword(), user.getRole().getName().toUpperCase())
                )
        );
        tokenDTO.setTokenType(tokenUtil.getTokenPrefix());
        tokenDTO.setUser(userMapper.toDTO(user));

        return tokenDTO;
    }

    @Transactional
    public UserDTO update(Integer userId, UserUpdateDTO userUpdateDTO, HttpServletRequest request) {
        User user = userRepository.getOne(userId);

        if (!passwordEncoder.matches(userUpdateDTO.getOldPassword(), user.getPassword())){
            throw new OldPasswordIncorrectException("Old password is incorrect!");
        }

        if (userUpdateDTO.getNewPassword() != null) {
            userUpdateDTO.setNewPassword(passwordEncoder.encode(userUpdateDTO.getNewPassword()));
        }
        user = userUpdateMapper.toEntity(user, userUpdateDTO);

        if (userUpdateDTO.getEmail() != null && !userUpdateDTO.getEmail().isEmpty() && !userUpdateDTO.getEmail().equals(user.getEmail())){
            user.setConfirmation(false);
            UUID uuid = UUID.randomUUID();
            user.setConfirmCode(uuid.toString());
            emailService.sendEmail(user.getEmail(), user.getConfirmCode(), request.getRequestURL().toString());
        }

        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    public UserDTO changeRole(Integer userId, RoleEnum role) {
        User user = userRepository.getOne(userId);

        user.setRole(roleRepository.findOneByName(role.name()));
        return userMapper.toDTO(userRepository.save(user));
    }
}