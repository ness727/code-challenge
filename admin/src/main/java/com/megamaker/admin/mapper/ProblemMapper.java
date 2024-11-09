package com.megamaker.admin.mapper;

import com.megamaker.admin.domain.Level;
import com.megamaker.admin.dto.RequestProblem;
import com.megamaker.admin.entity.Problem;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProblemMapper {
    ProblemMapper INSTANCE = Mappers.getMapper(ProblemMapper.class);

    Problem toProblem(RequestProblem requestProblem);
}
