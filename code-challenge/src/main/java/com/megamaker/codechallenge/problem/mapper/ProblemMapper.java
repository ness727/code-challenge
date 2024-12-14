package com.megamaker.codechallenge.problem.mapper;

import com.megamaker.codechallenge.problem.domain.vo.Level;
import com.megamaker.codechallenge.problem.domain.dto.ResponseListProblem;
import com.megamaker.codechallenge.problem.domain.dto.ResponseProblem;
import com.megamaker.codechallenge.problem.domain.dto.ResponseProblemPicture;
import com.megamaker.codechallenge.problem.domain.dto.ResponseTestcase;
import com.megamaker.codechallenge.problem.domain.Problem;
import com.megamaker.codechallenge.problem.domain.vo.ProblemPicture;
import com.megamaker.codechallenge.problem.domain.vo.Testcase;
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
