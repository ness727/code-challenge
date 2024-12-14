package com.megamaker.codechallenge.web.securityconfig.oauth2;

import com.megamaker.codechallenge.web.securityconfig.ProviderEnum;

public interface OAuth2Response {
    ProviderEnum getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}
