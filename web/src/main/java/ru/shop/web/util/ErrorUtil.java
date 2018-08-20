package ru.shop.web.util;

import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.shop.web.model.error.Error;
import ru.shop.web.model.error.ErrorMap;

import java.io.IOException;
import java.lang.annotation.Annotation;

@Component
public class ErrorUtil {
    private Retrofit retrofit;

    @Autowired
    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public Error parseError(Response<?> response) {
        Converter<ResponseBody, Error> converter = retrofit.responseBodyConverter(Error.class, new Annotation[0]);

        Error error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new Error();
        }

        return error;
    }

    public ErrorMap parseErrorMap(Response<?> response) {
        Converter<ResponseBody, ErrorMap> converter = retrofit.responseBodyConverter(ErrorMap.class, new Annotation[0]);

        ErrorMap error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorMap();
        }

        return error;
    }
}