package com.megamaker.codechallenge.securityconfig.oauth2;

import com.megamaker.codechallenge.securityconfig.ProviderEnum;

public class TestResponse implements OAuth2Response {

    @Override
    public ProviderEnum getProvider() {
        return ProviderEnum.TEST;
    }

    @Override
    public String getProviderId() {
        return "test";
    }

    @Override
    public String getEmail() {
        return "test";
    }

    @Override
    public String getName() {
        return "test";
    }
}
