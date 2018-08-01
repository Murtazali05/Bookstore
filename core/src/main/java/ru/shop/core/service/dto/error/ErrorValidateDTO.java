package ru.shop.core.service.dto.error;

public class ErrorValidateDTO extends ErrorDTO {
    private String field;
    private String code;

    public ErrorValidateDTO(String name, String message, String field, String code) {
        super(name, message);
        this.field = field;
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
