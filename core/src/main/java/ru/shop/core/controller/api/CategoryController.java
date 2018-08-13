package ru.shop.core.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shop.core.service.CategoryService;
import ru.shop.core.service.dto.category.CategoryDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Api(tags = "Categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ApiOperation("Получить все категории книг")
    public List<CategoryDTO> getAll(){
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    @ApiOperation("Получить категорию по ID")
    public CategoryDTO get(@PathVariable("id") Integer id) throws NotFoundException {
        return categoryService.getCategory(id);
    }

    @PostMapping
    @ApiOperation("Добавить категорию")
    public CategoryDTO create(@Valid @RequestBody CategoryDTO categoryDTO){
        return categoryService.create(categoryDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation("Изменить категорию")
    public CategoryDTO update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable("id") Integer id) throws NotFoundException {
        return categoryService.update(id, categoryDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удалить категорию по ID")
    public CategoryDTO delete(@PathVariable("id") Integer id) throws NotFoundException {
        return categoryService.delete(id);
    }
}
