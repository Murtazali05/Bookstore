package ru.shop.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.persistense.repository.PhotoRepository;
import ru.shop.service.dto.PhotoDTO;
import ru.shop.service.mapper.PhotoMapper;

@Service
public class PhotoService {
    private PhotoRepository photoRepository;
    private PhotoMapper photoMapper;

    @Autowired
    public void setPhotoRepository(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Autowired
    public void setPhotoMapper(PhotoMapper photoMapper) {
        this.photoMapper = photoMapper;
    }

    @Transactional
    public boolean existPhotoById(Integer photoId){
        return photoRepository.existsById(photoId);
    }

    @Transactional(readOnly = true)
    public PhotoDTO getPhoto(Integer id) throws NotFoundException {
        if (!photoRepository.existsById(id))
            throw new NotFoundException("The photo not found!");

        return photoMapper.toDTO(photoRepository.getOne(id));
    }

}
