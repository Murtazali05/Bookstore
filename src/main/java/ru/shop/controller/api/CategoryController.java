package ru.shop.controller.api;

import io.swagger.annotations.Api;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shop.service.CategoryService;
import ru.shop.service.dto.category.CategoryDTO;

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
    public List<CategoryDTO> getAll(){
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public CategoryDTO get(@PathVariable("id") Integer id) throws NotFoundException {
        return categoryService.getCategory(id);
    }

    @PostMapping
    public CategoryDTO create(@Valid @RequestBody CategoryDTO categoryDTO){
        return categoryService.create(categoryDTO);
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable("id") Integer id) throws NotFoundException {
        return categoryService.update(id, categoryDTO);
    }

    @DeleteMapping("/{id}")
    public CategoryDTO delete(@PathVariable("id") Integer id) throws NotFoundException {
        return categoryService.delete(id);
    }

}
