package com.megamaker.codechallenge.user.mapper;

import com.megamaker.codechallenge.badge.domain.Badge;
import com.megamaker.codechallenge.common.UserBadge;
import com.megamaker.codechallenge.user.domain.dto.ResponseBadge;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

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
