package ru.shop.core.service.dto.error;

import java.util.Map;

public class ErrorMapDTO extends ErrorDTO {
    private Map<String, String> errors;

    public ErrorMapDTO(String exception, String message, Map<String, String> errors) {
        super(exception, message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
