package com.megamaker.admin.config.security;

import lombok.Getter;

@Getter
public enum ProviderEnum {
    NONE("none"),
    GITHUB("github"),
    GOOGLE("google");

    private final String registrationId;

    ProviderEnum(String registrationId) {
        this.registrationId = registrationId;
    }

}
