package ru.shop.service.mapper.user;

import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.User;
import ru.shop.service.dto.user.UserDTO;
import ru.shop.service.mapper.AbstractMapper;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO> {

    public UserMapper() {
        super(User.class, UserDTO.class);
    }

}
