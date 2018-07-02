package ru.shop.service.mapper.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Category;
import ru.shop.persistense.repository.CategoryRepository;
import ru.shop.service.dto.category.CategoryDTO;
import ru.shop.service.mapper.AbstractMapper;

@Component
public class CategoryMapper extends AbstractMapper<Category, CategoryDTO> {
    @Autowired
    private CategoryRepository categoryRepository;

    CategoryMapper() {
        super(Category.class, CategoryDTO.class);
    }

    @Override
    public CategoryDTO toDTO(Category category) {
        CategoryDTO categoryDTO = super.toDTO(category);
        if (category.getParent() != null)
            categoryDTO.setParentId(category.getParent().getId());

        return categoryDTO;
    }

    @Override
    public Category toEntity(CategoryDTO categoryDTO) {
        Category category = super.toEntity(categoryDTO);

        if (categoryDTO.getParentId() != null) {
            category.setParent(categoryRepository.getOne(categoryDTO.getParentId()));
        }

        return category;
    }

}
