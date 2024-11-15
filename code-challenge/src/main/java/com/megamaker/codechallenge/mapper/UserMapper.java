package com.megamaker.codechallenge.mapper;

import com.megamaker.codechallenge.dto.user.ResponseUserRank;
import com.megamaker.codechallenge.dto.user.ResponseUser;
import com.megamaker.codechallenge.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = BadgeMapper.class)
public interface UserMapper {
    ResponseUser toResponseUser(User user);

    ResponseUserRank toResponseUserRank(User user);
}
