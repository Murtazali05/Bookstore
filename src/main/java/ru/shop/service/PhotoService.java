package ru.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.persistense.repository.PhotoRepository;

@Service
public class PhotoService {
    private PhotoRepository photoRepository;

    @Autowired
    public void setPhotoRepository(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Transactional
    public boolean existPhotoById(Integer photoId){
        return photoRepository.existsById(photoId);
    }

}
