package ru.shop.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.persistense.entity.Category;
import ru.shop.persistense.repository.CategoryRepository;
import ru.shop.service.dto.category.CategoryDTO;
import ru.shop.service.dto.category.CategorySaveDTO;
import ru.shop.service.mapper.category.CategoryMapper;
import ru.shop.service.mapper.category.CategorySaveMapper;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private CategorySaveMapper categorySaveMapper;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Autowired
    public void setCategorySaveMapper(CategorySaveMapper categorySaveMapper) {
        this.categorySaveMapper = categorySaveMapper;
    }

    @Transactional(readOnly = true)
    public List<CategoryDTO> getCategories(){
        return categoryMapper.toDTOs(categoryRepository.findAll());
    }

    @Transactional(readOnly = true)
    public CategoryDTO getCategory(Integer id) throws NotFoundException {
        if (!categoryRepository.existsById(id))
            throw new NotFoundException("Category with such id=" + id + " does not found!");

        return categoryMapper.toDTO(categoryRepository.getOne(id));
    }

    @Transactional
    public CategoryDTO create(CategorySaveDTO categoryDTO) {
        Category category = categoryRepository.save(categorySaveMapper.toEntity(categoryDTO));
        return categoryMapper.toDTO(category);
    }

    @Transactional
    public CategoryDTO update(Integer categoryId, CategorySaveDTO categoryDTO) throws NotFoundException {
        if (!categoryRepository.existsById(categoryId))
            throw new NotFoundException("Category with such id=" + categoryId + " does not exist!");

        Category category = categorySaveMapper.toEntity(categoryId, categoryDTO);
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Transactional
    public CategoryDTO delete(Integer categoryId) throws NotFoundException {
        if (!categoryRepository.existsById(categoryId))
            throw new NotFoundException("Category with such id=" + categoryId + " does not exist!");

        Category category = categoryRepository.getOne(categoryId);
        categoryRepository.delete(category);
        return categoryMapper.toDTO(category);
    }

}
