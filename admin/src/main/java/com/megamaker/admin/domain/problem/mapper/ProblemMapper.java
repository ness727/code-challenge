package com.megamaker.admin.domain.problem.mapper;

import com.megamaker.admin.domain.problem.dto.RequestProblem;
import com.megamaker.admin.domain.problem.dto.RequestTestcase;
import com.megamaker.admin.domain.problem.dto.ResponseListProblem;
import com.megamaker.admin.domain.problem.dto.ResponseProblem;
import com.megamaker.admin.domain.problem.Problem;
import com.megamaker.admin.domain.problem.vo.Testcase;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProblemMapper {
    Problem toProblem(RequestProblem requestProblem);

    ResponseListProblem toResponseListProblem(Problem problem);

    ResponseProblem toResponseProblem(Problem problem);

    Testcase toTestcase(RequestTestcase requestTestcase);
}
