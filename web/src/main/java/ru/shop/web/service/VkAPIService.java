package ru.shop.web.service;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.users.UserField;
import org.springframework.stereotype.Service;
import ru.shop.web.model.Photo;
import ru.shop.web.model.user.TokenUser;
import ru.shop.web.model.user.User;

@Service
public class VkAPIService {
    private static final Integer APP_ID = 6670676;
    private static final String CLIENT_SECRET = "aPYXmoMDGxQAWtb6wxQ7";
    private static final String REDIRECT_URI = "http://localhost:8081/vk/login";

    public TokenUser loginVk(String code) throws ClientException, ApiException {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);

        UserAuthResponse authResponse = vk.oauth()
                .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
                .execute();

        TokenUser tokenUser = new TokenUser();
        tokenUser.setAccessToken(authResponse.getAccessToken());
        tokenUser.setTokenType("Bearer");

        tokenUser.setUser(getUserByVkProfile(vk, authResponse));

        return tokenUser;
    }

    private User getUserByVkProfile(VkApiClient vk, UserAuthResponse authResponse) throws ClientException, ApiException {
        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());

        UserXtrCounters userXtrCounters = vk.users().get(actor)
                .fields(UserField.BDATE, UserField.PHOTO_ID, UserField.PHOTO_200)
                .execute()
                .get(0);

        User user = new User();
        user.setId(authResponse.getUserId());
        user.setSurname(userXtrCounters.getLastName());
        user.setName(userXtrCounters.getFirstName());
        user.setEmail(authResponse.getEmail());
//        user.setBirthday(Date.valueOf(userXtrCounters.getBdate()));

        String photoId = userXtrCounters.getPhotoId().substring(0, userXtrCounters.getPhotoId().indexOf("_"));
        user.setPhoto(new Photo(Integer.valueOf(photoId), userXtrCounters.getPhoto200()));

        return user;
    }
}
