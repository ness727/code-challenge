package com.megamaker.codechallenge.domain.user.vo;

import com.megamaker.codechallenge.securityconfig.ProviderEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class Provider {
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "provider")
    private ProviderEnum providerEnum;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "provider_nickname")
    private String providerNickname;
}
