package com.megamaker.codechallenge.dto.oauth2;

import com.megamaker.codechallenge.securityconfig.Provider;

public interface OAuth2Response {
    Provider getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}
