package ru.shop.web.model.error;

import java.util.Map;

public class ErrorMap extends Error {
    private Map<String, String> errors;

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
