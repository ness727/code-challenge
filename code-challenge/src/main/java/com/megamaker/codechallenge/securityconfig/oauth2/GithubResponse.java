package com.megamaker.codechallenge.securityconfig.oauth2;

import com.megamaker.codechallenge.securityconfig.Provider;

import java.util.Map;

public class GithubResponse implements OAuth2Response {
    private final Map<String, Object> attribute;

    public GithubResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public Provider getProvider() {
        return Provider.GITHUB;
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("login").toString();
    }
}
