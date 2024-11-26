package com.megamaker.admin.domain.problem.mapper;

import com.megamaker.admin.domain.problem.vo.Level;
import com.megamaker.admin.domain.problem.vo.Testcase;
import com.megamaker.admin.domain.problem.dto.RequestProblemUpdate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.ArrayList;
import java.util.List;

public class ProblemUpdateArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(ProblemUpdate.class);
        boolean assignableFrom = RequestProblemUpdate.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && assignableFrom;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        // testcase 바인딩
        //String[] tcIdx = request.getParameterValues("testcase.idx");
        String[] tcParamData = request.getParameterValues("testcase.paramData");
        String[] tcResult = request.getParameterValues("testcase.result");

        List<Testcase> testcaseList = new ArrayList<>();
        for (int i = 0; i < tcResult.length; i++) {
            testcaseList.add(new Testcase(tcParamData[i], tcResult[i]));
        }

        // 나머지 RequestProblemUpdate 바인딩
        return RequestProblemUpdate.builder()
                .id(Long.valueOf(request.getParameter("id")))
                .title(request.getParameter("title"))
                .level(Level.valueOf(request.getParameter("level")))
                .score(Byte.valueOf(request.getParameter("score")))
                .params(request.getParameter("params"))
                .returnType(request.getParameter("returnType"))
                .description(request.getParameter("description"))
                .limitation(request.getParameter("limitation"))
                .inputOutput(request.getParameter("inputOutput"))
                .testcaseList(testcaseList)
                .build();
    }
}
