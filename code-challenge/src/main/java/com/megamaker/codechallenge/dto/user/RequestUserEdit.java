package com.megamaker.codechallenge.dto.user;

import com.megamaker.codechallenge.domain.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserEdit {
    private String providerId;
    private String nickname;
}
