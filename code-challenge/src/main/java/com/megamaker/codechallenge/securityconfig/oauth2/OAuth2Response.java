package com.megamaker.codechallenge.securityconfig.oauth2;

import com.megamaker.codechallenge.securityconfig.ProviderEnum;

public interface OAuth2Response {
    ProviderEnum getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}
