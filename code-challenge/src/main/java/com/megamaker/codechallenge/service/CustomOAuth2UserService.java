package com.megamaker.codechallenge.service;

import com.megamaker.codechallenge.domain.OAuth2ClientEnum;
import com.megamaker.codechallenge.dto.CustomOAuth2User;
import com.megamaker.codechallenge.dto.OAuth2Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = OAuth2ClientEnum.getOAuth2Response(oAuth2User, registrationId);
        log.info("OAuth2User.getAttributes = {}", oAuth2User.getAttributes());

        // 나중에 enum으로 분리하기
        String role = "ROLE_USER";

        return new CustomOAuth2User(oAuth2Response, role);
    }
}
