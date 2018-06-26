package ru.shop.service.mapper.category;

import org.springframework.stereotype.Component;
import ru.shop.persistense.entity.Category;
import ru.shop.service.dto.category.CategoryDTO;
import ru.shop.service.mapper.AbstractMapper;

@Component
public class CategoryMapper extends AbstractMapper<Category, CategoryDTO> {

    CategoryMapper() {
        super(Category.class, CategoryDTO.class);
    }

}
