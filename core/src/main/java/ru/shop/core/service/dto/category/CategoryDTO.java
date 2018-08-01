package ru.shop.core.service.dto.category;

import ru.shop.core.persistense.entity.Category;
import ru.shop.core.validator.annotation.ExistEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategoryDTO {
    private int id;

    @NotBlank
    @Size(min=3, max=45)
    private String name;

    @NotBlank
    @Size(min=3, max=45)
    private String code;

    @ExistEntity(entityClass = Category.class)
    private Integer parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
