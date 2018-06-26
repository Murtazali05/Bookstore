package ru.shop.controller.api;

import io.swagger.annotations.Api;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.shop.service.PhotoService;
import ru.shop.service.dto.PageDTO;
import ru.shop.service.dto.PageShortDTO;
import ru.shop.service.dto.PhotoDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/photo")
@Api(tags = "Photo")
public class PhotoController {
    private PhotoService photoService;

    @Autowired
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/{id}")
    public PhotoDTO get(@PathVariable Integer id) throws NotFoundException {
        return photoService.getPhoto(id);
    }

    @PostMapping
    public PhotoDTO upload(@RequestParam MultipartFile file){
        return new PhotoDTO();
    }

}
