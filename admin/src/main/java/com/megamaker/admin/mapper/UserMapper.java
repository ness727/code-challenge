package com.megamaker.admin.mapper;

import com.megamaker.admin.dto.user.ResponseListUser;
import com.megamaker.admin.dto.user.ResponseUser;
import com.megamaker.admin.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    ResponseListUser toResponseListUser(User user);

    ResponseUser toResponseUser(User user);
}
