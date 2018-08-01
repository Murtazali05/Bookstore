package ru.shop.core.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.core.persistense.entity.Genre;
import ru.shop.core.persistense.repository.GenreRepository;
import ru.shop.core.service.dto.genre.GenreDTO;
import ru.shop.core.service.mapper.genre.GenreMapper;

import java.util.List;

@Service
public class GenreService {
    private GenreRepository genreRepository;
    private GenreMapper genreMapper;

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Autowired
    public void setGenreMapper(GenreMapper genreMapper) {
        this.genreMapper = genreMapper;
    }

    @Transactional(readOnly = true)
    public List<GenreDTO> getGenres(){
        return genreMapper.toDTOs(genreRepository.findAll());
    }

    @Transactional(readOnly = true)
    public GenreDTO getGenre(Integer id) throws NotFoundException {
        if (!genreRepository.existsById(id))
            throw new NotFoundException("Genres with such id=" + id + " does not found!");

        return genreMapper.toDTO(genreRepository.getOne(id));
    }

    @Transactional
    public GenreDTO create(GenreDTO genreDTO) {
        Genre genre = genreRepository.save(genreMapper.toEntity(genreDTO));
        return genreMapper.toDTO(genre);
    }

    @Transactional
    public GenreDTO update(Integer genreId, GenreDTO genreDTO) throws NotFoundException {
        if (!genreRepository.existsById(genreId))
            throw new NotFoundException("Genre with such id=" + genreId + " does not exist!");

        genreDTO.setId(genreId);
        Genre genre = genreMapper.toEntity(genreDTO);
        return genreMapper.toDTO(genreRepository.save(genre));
    }

    @Transactional
    public GenreDTO delete(Integer genreId) throws NotFoundException {
        if (!genreRepository.existsById(genreId))
            throw new NotFoundException("Genre with such id=" + genreId + " does not exist!");

        Genre genre = genreRepository.getOne(genreId);
        genreRepository.delete(genre);
        return genreMapper.toDTO(genre);
    }

}
