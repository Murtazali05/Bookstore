package ru.shop.core.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.shop.core.persistense.entity.Photo;
import ru.shop.core.persistense.repository.PhotoRepository;
import ru.shop.core.service.dto.PhotoDTO;
import ru.shop.core.service.mapper.PhotoMapper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

@Service
public class PhotoService {
    private final static String USER_HOME = System.getProperty("user.home");
    private final static String prefix = "photo_";
    private final static String suffix = ".jpg";
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

    @Transactional(readOnly = true)
    public Resource getResource(Integer id) throws NotFoundException, MalformedURLException {
        if (!photoRepository.existsById(id))
            throw new NotFoundException("The photo not found!");

        PhotoDTO photoDTO = photoMapper.toDTO(photoRepository.getOne(id));

        Path photoPath = Paths.get(photoDTO.getPath()).normalize();
        return new UrlResource(photoPath.toUri());
    }

    @Transactional(readOnly = true)
    public PhotoDTO getPhoto(Integer id) throws NotFoundException {
        if (!photoRepository.existsById(id))
            throw new NotFoundException("The photo not found!");

        return photoMapper.toDTO(photoRepository.getOne(id));
    }

    @Transactional
    public PhotoDTO create(PhotoDTO photoDTO) {
        Photo photo = photoRepository.save(photoMapper.toEntity(photoDTO));
        return photoMapper.toDTO(photo);
    }

    @Transactional
    public PhotoDTO upload(MultipartFile file) throws IOException {
        String contentType = file.getContentType().split("/")[0];
        if (!contentType.equals("image"))
            throw new IllegalArgumentException("The type=" + contentType + " of the passed variable is incorrect");

        byte[] bytes = file.getBytes();
        String relativePath = this.getUniquePath();
        Path path = Paths.get(USER_HOME + relativePath);
        Files.write(path, bytes);

        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setPath(relativePath);
        photoDTO.setSize((int) file.getSize());

        return this.create(photoDTO);
    }

    private String getUniquePath() throws IOException {
        String pathToFile = "/upload/photo/" + String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) +
                "/" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1) +
                "/" + String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) + "/";

        File folders = new File(USER_HOME + pathToFile);

        if (!folders.exists()) {
            if (!folders.mkdirs()) throw new IOException("Сould not create path!");
        }

        File file = File.createTempFile(prefix, suffix, folders);

        return pathToFile + file.getName();
    }

    @Transactional
    public void delete(Integer id) throws NotFoundException {
        if (!photoRepository.existsById(id))
            throw new NotFoundException("The photo not found!");

        PhotoDTO photoDTO = photoMapper.toDTO(photoRepository.getOne(id));

        try {
            Files.delete(Paths.get(USER_HOME + photoDTO.getPath()));
        } catch (IOException ignored) {

        } finally {
            photoRepository.deleteById(id);
        }
    }

}