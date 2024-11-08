package com.megamaker.codechallenge.securityconfig;

import com.megamaker.codechallenge.domain.user.Role;
import com.megamaker.codechallenge.entity.User;
import com.megamaker.codechallenge.repository.UserRepository;
import com.megamaker.codechallenge.securityconfig.oauth2.CustomOAuth2User;
import com.megamaker.codechallenge.securityconfig.oauth2.OAuth2Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = Provider.getOAuth2Response(oAuth2User, registrationId);
        // log.info("OAuth2User.getAttributes = {}", oAuth2User.getAttributes());

        Optional<User> foundUser = userRepository.findByProviderId(oAuth2Response.getProviderId());
        Role role;
        if (foundUser.isEmpty()) {
            User newUser = User.builder()
                    .provider(oAuth2Response.getProvider())
                    .providerId(oAuth2Response.getProviderId())
                    .nickname(oAuth2Response.getName())
                    .score(0)
                    .role(Role.USER)
                    .build();
            userRepository.save(newUser);
            role = Role.USER;
        } else {
            role = foundUser.get().getRole();
        }

        return new CustomOAuth2User(oAuth2Response, role.name());
    }
}
