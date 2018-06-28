package ru.shop.controller.api;

import io.swagger.annotations.Api;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.shop.service.PhotoService;
import ru.shop.service.dto.PhotoDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api/photo")
@Api(tags = "Photo")
public class PhotoController {
    private static final String UPLOADED_FOLDER = "U:\\upload\\photo\\";
    private static final String prefix = "photo";
    private static final String suffix = ".jpg";
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
    public PhotoDTO upload(@NotNull @NotEmpty @RequestParam MultipartFile file) throws IOException {

        byte[] bytes = file.getBytes();
        Path path = this.getUniquePath();
        Files.write(path, bytes);

        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setPath(path.toAbsolutePath().toString());
        photoDTO.setSize((int) file.getSize());

        return photoService.create(photoDTO);
    }

    private Path getUniquePath() throws IOException {
        String newDirectories = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) +
                "\\" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1) +
                "\\" + String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) + "\\";

        File folders = new File(UPLOADED_FOLDER + newDirectories);

        if (!folders.exists()) {
            if (!folders.mkdirs()) throw new IOException("Сould not create path!");
        }

        File file = File.createTempFile(prefix, suffix, folders);

        return Paths.get(file.getAbsolutePath());
    }

}
