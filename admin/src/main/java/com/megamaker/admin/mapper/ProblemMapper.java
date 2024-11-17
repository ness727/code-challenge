package com.megamaker.admin.mapper;

import com.megamaker.admin.domain.Level;
import com.megamaker.admin.dto.RequestProblem;
import com.megamaker.admin.dto.RequestTestcase;
import com.megamaker.admin.dto.problem.ResponseProblem;
import com.megamaker.admin.entity.Problem;
import com.megamaker.admin.entity.Testcase;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProblemMapper {
    Problem toProblem(RequestProblem requestProblem);

    ResponseProblem toResponseProblem(Problem problem);

    Testcase toTestcase(RequestTestcase requestTestcase);
}
