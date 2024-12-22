package com.megamaker.codechallenge.user.domain.vo;

import com.megamaker.codechallenge.web.securityconfig.ProviderEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Provider {
    private ProviderEnum provider;

    private String providerId;

    private String providerNickname;
}
