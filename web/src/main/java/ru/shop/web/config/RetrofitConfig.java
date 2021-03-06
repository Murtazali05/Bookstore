package ru.shop.web.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {
    @Value("${core.endpoint}")
    private String coreEndpoint;

    private JwtInterceptor interceptor;

    @Autowired
    public void setInterceptor(JwtInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Bean
    @Autowired
    public Retrofit retrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(coreEndpoint)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Bean
    public OkHttpClient client() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        return okHttpClientBuilder.addInterceptor(interceptor).build();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().setLenient().create();
    }
}
