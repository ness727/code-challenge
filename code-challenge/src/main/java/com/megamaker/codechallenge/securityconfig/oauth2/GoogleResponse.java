package com.megamaker.codechallenge.securityconfig.oauth2;

import com.megamaker.codechallenge.securityconfig.ProviderEnum;

import java.util.Map;

public class GoogleResponse implements OAuth2Response {
    private final Map<String, Object> attribute;

    public GoogleResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public ProviderEnum getProvider() {
        return ProviderEnum.GOOGLE;
    }

    @Override
    public String getProviderId() {
        return attribute.get("sub").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }
}
