package ru.shop.core.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.shop.core.service.PhotoService;
import ru.shop.core.service.dto.PhotoDTO;

import java.io.IOException;

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
    @ApiOperation("Получить информацию о изображении по ID")
    public PhotoDTO get(@PathVariable("id") Integer id) throws NotFoundException {
        return photoService.getPhoto(id);
    }

    @PostMapping
    @ApiOperation("Загрузить изображение")
    public PhotoDTO upload(@RequestParam MultipartFile file) throws IOException {
        return photoService.upload(file);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удалить изображение по ID")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws NotFoundException {
        photoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
