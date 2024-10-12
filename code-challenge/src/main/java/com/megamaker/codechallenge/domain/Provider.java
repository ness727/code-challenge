package com.megamaker.codechallenge.domain;

import com.megamaker.codechallenge.dto.GithubResponse;
import com.megamaker.codechallenge.dto.GoogleResponse;
import com.megamaker.codechallenge.dto.OAuth2Response;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;

public enum Provider {
    GITHUB("github"),
    GOOGLE("google");

    private final String registrationId;

    Provider(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    private static Provider getOAuth2Client(String registrationId) {
        return Arrays.stream(Provider.values())
                .filter((e) -> e.getRegistrationId().equals(registrationId))
                .findFirst()
                .orElse(null);
    }

    public static OAuth2Response getOAuth2Response(OAuth2User oAuth2User, String registrationId) {
        return switch (getOAuth2Client(registrationId)) {
            case GITHUB -> new GithubResponse(oAuth2User.getAttributes());
            case GOOGLE -> new GoogleResponse(oAuth2User.getAttributes());
        };
    }
}
