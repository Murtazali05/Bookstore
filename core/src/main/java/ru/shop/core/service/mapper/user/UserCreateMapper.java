package ru.shop.core.service.mapper.user;

import org.springframework.stereotype.Component;
import ru.shop.core.persistense.entity.User;
import ru.shop.core.service.dto.user.UserCreateDTO;
import ru.shop.core.service.mapper.AbstractMapper;

@Component
public class UserCreateMapper extends AbstractMapper<User, UserCreateDTO> {

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
        return user;
    }

}
