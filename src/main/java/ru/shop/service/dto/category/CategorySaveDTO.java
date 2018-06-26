package ru.shop.service.dto.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CategorySaveDTO {
    @NotBlank
    @Size(min=3, max=30)
    private String name;

    @NotBlank
    @Size(min=3, max=30)
    private String code;

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
}
