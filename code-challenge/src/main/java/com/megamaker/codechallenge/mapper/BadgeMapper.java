package com.megamaker.codechallenge.mapper;

import com.megamaker.codechallenge.domain.entity.Badge;
import com.megamaker.codechallenge.domain.entity.User;
import com.megamaker.codechallenge.domain.entity.UserBadge;
import com.megamaker.codechallenge.dto.user.ResponseBadge;
import com.megamaker.codechallenge.dto.user.ResponseUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BadgeMapper {
    @Mapping(source = "id", target = "name")
    ResponseBadge toResponseBadge(Badge badge);

    default List<ResponseBadge> toResponseBadgeList(List<UserBadge> userBadgeList) {
        return userBadgeList.stream()
                .map(userBadge -> {
                    ResponseBadge responseBadge = toResponseBadge(userBadge.getBadge());
                    responseBadge.setAchievedDate(userBadge.getCreatedAt().toLocalDate());
                    return responseBadge;
                })
                .toList();
    }
}
