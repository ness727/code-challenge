package com.megamaker.codechallenge.domain.problem.mapper;

import com.megamaker.codechallenge.domain.problem.vo.Level;
import com.megamaker.codechallenge.domain.problem.dto.ResponseListProblem;
import com.megamaker.codechallenge.domain.problem.dto.ResponseProblem;
import com.megamaker.codechallenge.domain.problem.dto.ResponseProblemPicture;
import com.megamaker.codechallenge.domain.problem.dto.ResponseTestcase;
import com.megamaker.codechallenge.domain.problem.Problem;
import com.megamaker.codechallenge.domain.problem.vo.ProblemPicture;
import com.megamaker.codechallenge.domain.problem.vo.Testcase;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProblemMapper {
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
