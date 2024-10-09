package com.megamaker.codechallenge.mapper;

import com.megamaker.codechallenge.domain.Level;
import com.megamaker.codechallenge.dto.ResponseListProblem;
import com.megamaker.codechallenge.dto.ResponseProblem;
import com.megamaker.codechallenge.dto.ResponseProblemPicture;
import com.megamaker.codechallenge.dto.ResponseTestcase;
import com.megamaker.codechallenge.entity.Problem;
import com.megamaker.codechallenge.entity.ProblemPicture;
import com.megamaker.codechallenge.entity.Testcase;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProblemMapper {
    ProblemMapper INSTANCE = Mappers.getMapper(ProblemMapper.class);

    @Mapping(source = "level", target = "level", qualifiedByName = "ToStringLevel")
    ResponseListProblem toResponseListProblem(Problem problem);

    @Named("ToStringLevel")
    default String toStringLevel(Level level) {
        return level.getLevelString();
    }

    ResponseProblem toResponseProblem(Problem problem);

    ResponseProblemPicture toResponseProblemPicture(ProblemPicture problemPicture);

    ResponseTestcase toResponseTestcase(Testcase testcase);
}
