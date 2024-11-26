package com.megamaker.codechallenge.securityconfig;

import com.megamaker.codechallenge.securityconfig.oauth2.GithubResponse;
import com.megamaker.codechallenge.securityconfig.oauth2.GoogleResponse;
import com.megamaker.codechallenge.securityconfig.oauth2.OAuth2Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;

public enum ProviderEnum {
    TEST("test"),
    GITHUB("github"),
    GOOGLE("google");

    private final String registrationId;

    ProviderEnum(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    private static ProviderEnum getOAuth2Client(String registrationId) {
        return Arrays.stream(ProviderEnum.values())
                .filter((e) -> e.getRegistrationId().equals(registrationId))
                .findFirst()
                .orElse(null);
    }

    public static OAuth2Response getOAuth2Response(OAuth2User oAuth2User, String registrationId) {
        return switch (getOAuth2Client(registrationId)) {
            case GITHUB -> new GithubResponse(oAuth2User.getAttributes());
            case GOOGLE -> new GoogleResponse(oAuth2User.getAttributes());
            default -> null;
        };
    }
}
