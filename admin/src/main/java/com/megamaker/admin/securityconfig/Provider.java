package com.megamaker.admin.securityconfig;

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
