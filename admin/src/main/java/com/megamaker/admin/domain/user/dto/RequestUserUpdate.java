package com.megamaker.admin.domain.user.dto;

import com.megamaker.admin.domain.user.vo.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestUserUpdate {
    private Long id;

    private String nickname;

//    private Integer solveCount;
//
//    private Integer score;

    private Role role;
}
