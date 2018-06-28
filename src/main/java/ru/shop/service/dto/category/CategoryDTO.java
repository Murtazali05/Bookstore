package ru.shop.service.dto.category;

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

}
