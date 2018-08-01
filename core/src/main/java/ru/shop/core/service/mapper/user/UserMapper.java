package ru.shop.core.service.mapper.user;

import org.springframework.stereotype.Component;
import ru.shop.core.persistense.entity.User;
import ru.shop.core.service.dto.user.UserDTO;
import ru.shop.core.service.mapper.AbstractMapper;

@Component
public class UserMapper extends AbstractMapper<User, UserDTO> {

    public UserMapper() {
        super(User.class, UserDTO.class);
    }

}
