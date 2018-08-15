package ru.shop.web.controller.common;

public class AbstractController {

    protected String getPageByStatus(Integer status){
        return status.toString();
    }
}
