package ru.shop.web.service;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.Actor;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.account.UserSettings;
import org.springframework.stereotype.Service;

@Service
public class VkAPIService {
    private static final Integer APP_ID = 6669657;
    private static final String CLIENT_SECRET = "Jt42NxnDPMDllgmIixqy";
    private static final String REDIRECT_URI = "http://localhost:8081/login/vk";

    public Actor loginVk(String code){
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        UserAuthResponse authResponse = null;
        try {
            authResponse = vk.oauth()
                    .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
                    .execute();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
        assert authResponse != null;
        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        try {
            UserSettings userSettings = vk.account().getProfileInfo(actor).execute();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }


        return actor;
    }
}
