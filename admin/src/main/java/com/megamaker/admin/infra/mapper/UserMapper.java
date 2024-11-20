package com.megamaker.admin.infra.mapper;

import com.megamaker.admin.domain.user.dto.ResponseListUser;
import com.megamaker.admin.domain.user.dto.ResponseUser;
import com.megamaker.admin.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    ResponseListUser toResponseListUser(User user);

    ResponseUser toResponseUser(User user);
}
