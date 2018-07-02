package ru.shop.service.mapper.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.User;
import ru.shop.persistense.repository.RoleRepository;
import ru.shop.service.dto.user.UserCreateDTO;
import ru.shop.service.mapper.AbstractMapper;

import java.util.UUID;

@Component
public class UserCreateMapper extends AbstractMapper<User, UserCreateDTO> {
    @Autowired
    private RoleRepository roleRepository;

    public UserCreateMapper() {
        super(User.class, UserCreateDTO.class);
    }

    @Override
    public User toEntity(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setSurname(userCreateDTO.getSurname());
        user.setName(userCreateDTO.getName());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(userCreateDTO.getPassword());
        user.setConfirmation(false);
        UUID uuid = UUID.randomUUID();
        user.setConfirmCode(uuid.toString());
        user.setRole(roleRepository.getOne(3));
        return user;
    }

}
