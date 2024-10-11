package com.megamaker.codechallenge.domain;

import com.megamaker.codechallenge.dto.GithubResponse;
import com.megamaker.codechallenge.dto.GoogleResponse;
import com.megamaker.codechallenge.dto.OAuth2Response;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;

public enum OAuth2ClientEnum {
    GITHUB("github"),
    GOOGLE("google");

    private final String registrationId;

    OAuth2ClientEnum(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public static OAuth2ClientEnum getOAuth2Client(String registrationId) {
        return Arrays.stream(OAuth2ClientEnum.values())
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
