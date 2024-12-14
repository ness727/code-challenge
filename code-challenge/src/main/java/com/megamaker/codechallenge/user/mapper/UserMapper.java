package com.megamaker.codechallenge.user.mapper;

import com.megamaker.codechallenge.user.domain.dto.ResponseUserRank;
import com.megamaker.codechallenge.user.domain.dto.ResponseUser;
import com.megamaker.codechallenge.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = BadgeMapper.class)
public interface UserMapper {
    ResponseUser toResponseUser(User user);

    ResponseUserRank toResponseUserRank(User user);
}
