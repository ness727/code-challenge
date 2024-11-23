package com.megamaker.admin.config.security;

public enum Provider {
    GITHUB("github"),
    GOOGLE("google");

    private final String registrationId;

    Provider(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getRegistrationId() {
        return registrationId;
    }
}
