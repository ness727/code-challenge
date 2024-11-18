package com.megamaker.admin.mapper;

import com.megamaker.admin.dto.RequestProblem;
import com.megamaker.admin.dto.RequestTestcase;
import com.megamaker.admin.dto.problem.ResponseListProblem;
import com.megamaker.admin.dto.problem.ResponseProblem;
import com.megamaker.admin.entity.Problem;
import com.megamaker.admin.entity.Testcase;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProblemMapper {
    Problem toProblem(RequestProblem requestProblem);

    ResponseListProblem toResponseListProblem(Problem problem);

    ResponseProblem toResponseProblem(Problem problem);

    Testcase toTestcase(RequestTestcase requestTestcase);
}
