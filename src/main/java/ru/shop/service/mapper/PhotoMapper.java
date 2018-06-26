package ru.shop.service.mapper;

import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Photo;
import ru.shop.service.dto.PhotoDTO;

@Component
public class PhotoMapper extends AbstractMapper<Photo, PhotoDTO> {

    PhotoMapper() {
        super(Photo.class, PhotoDTO.class);
    }

}
