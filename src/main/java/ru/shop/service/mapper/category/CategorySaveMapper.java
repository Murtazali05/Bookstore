package ru.shop.service.mapper.category;

import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Category;
import ru.shop.service.dto.category.CategorySaveDTO;
import ru.shop.service.mapper.AbstractMapper;

@Component
public class CategorySaveMapper extends AbstractMapper<Category, CategorySaveDTO> {

    public CategorySaveMapper() {
        super(Category.class, CategorySaveDTO.class);
    }

    @Override
    public Category toEntity(CategorySaveDTO categorySaveDTO) {
        Category category = super.toEntity(categorySaveDTO);
        category.setId(0);
        return category;
    }

}
