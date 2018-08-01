package ru.shop.core.service.mapper.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shop.core.persistense.entity.Category;
import ru.shop.core.persistense.repository.CategoryRepository;
import ru.shop.core.service.dto.category.CategoryDTO;
import ru.shop.core.service.mapper.AbstractMapper;

@Component
public class CategoryMapper extends AbstractMapper<Category, CategoryDTO> {
    private CategoryRepository categoryRepository;

    CategoryMapper() {
        super(Category.class, CategoryDTO.class);
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
