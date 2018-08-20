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
        if (userUpdateDTO.getSurname() != null && !userUpdateDTO.getSurname().isEmpty()) {
            user.setSurname(userUpdateDTO.getSurname());
        }
        if (userUpdateDTO.getName() != null && !userUpdateDTO.getName().isEmpty()) {
            user.setName(userUpdateDTO.getName());
        }
        if (userUpdateDTO.getEmail() != null && !userUpdateDTO.getEmail().isEmpty()) {
            user.setEmail(userUpdateDTO.getEmail());
        }
        if (userUpdateDTO.getNewPassword() != null && !userUpdateDTO.getNewPassword().isEmpty()) {
            user.setPassword(userUpdateDTO.getNewPassword());
        }
        if (userUpdateDTO.getBirthday() != null) {
            user.setBirthday(userUpdateDTO.getBirthday());
        }
        if (userUpdateDTO.getPhotoId() != null) {
            user.setPhoto(photoRepository.getOne(userUpdateDTO.getPhotoId()));
        }

        return user;
    }
}
