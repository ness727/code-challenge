package com.megamaker.codechallenge.mapper;

import com.megamaker.codechallenge.domain.Level;
import com.megamaker.codechallenge.dto.ResponseProblem;
import com.megamaker.codechallenge.entity.Problem;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProblemMapper {
    ProblemMapper INSTANCE = Mappers.getMapper(ProblemMapper.class);

    @Mapping(source = "level", target = "level", qualifiedByName = "ToStringLevel")
    ResponseProblem toResponseProblem(Problem problem);

    @Named("ToStringLevel")
    default String toStringLevel(Level level) {
        return level.getLevelString();
    }
}
