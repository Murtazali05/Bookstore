package ru.shop.core.service.mapper.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shop.core.persistense.entity.User;
import ru.shop.core.persistense.repository.PhotoRepository;
import ru.shop.core.service.dto.user.UserUpdateDTO;
import ru.shop.core.service.mapper.AbstractMapper;

@Component
public class UserUpdateMapper extends AbstractMapper<User, UserUpdateDTO> {
    private PhotoRepository photoRepository;

    public UserUpdateMapper() {
        super(User.class, UserUpdateDTO.class);
    }

    @Autowired
    public void setPhotoRepository(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public User toEntity(User user, UserUpdateDTO userUpdateDTO) {
        user.setSurname(userUpdateDTO.getSurname());
        user.setName(userUpdateDTO.getName());
        user.setEmail(userUpdateDTO.getEmail());
        user.setPassword(userUpdateDTO.getNewPassword());
        user.setBirthday(userUpdateDTO.getBirthday());
        user.setPhoto(photoRepository.getOne(userUpdateDTO.getPhotoId()));
        return user;
    }
}
