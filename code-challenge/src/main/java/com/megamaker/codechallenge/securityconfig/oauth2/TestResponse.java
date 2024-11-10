package com.megamaker.codechallenge.securityconfig.oauth2;

import com.megamaker.codechallenge.securityconfig.Provider;

import java.util.Map;

public class TestResponse implements OAuth2Response {

    @Override
    public Provider getProvider() {
        return Provider.TEST;
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
