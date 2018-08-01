package ru.shop.core.service.mapper;

import org.springframework.stereotype.Component;
import ru.shop.core.persistense.entity.Photo;
import ru.shop.core.service.dto.PhotoDTO;

@Component
public class PhotoMapper extends AbstractMapper<Photo, PhotoDTO> {

    PhotoMapper() {
        super(Photo.class, PhotoDTO.class);
    }

}
