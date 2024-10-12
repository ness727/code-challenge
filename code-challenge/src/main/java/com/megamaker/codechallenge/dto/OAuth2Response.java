package com.megamaker.codechallenge.dto;

import com.megamaker.codechallenge.domain.Provider;

public interface OAuth2Response {
    Provider getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}
