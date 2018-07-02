package ru.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.persistense.entity.User;
import ru.shop.persistense.repository.UserRepository;
import ru.shop.service.dto.user.UserCreateDTO;
import ru.shop.service.dto.user.UserDTO;
import ru.shop.service.mapper.user.UserCreateMapper;
import ru.shop.service.mapper.user.UserMapper;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private UserCreateMapper userCreateMapper;

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

    @Transactional
    public UserDTO create(UserCreateDTO userDTO) {
        User user = userRepository.save(userCreateMapper.toEntity(userDTO));
        return userMapper.toDTO(user);
    }

}