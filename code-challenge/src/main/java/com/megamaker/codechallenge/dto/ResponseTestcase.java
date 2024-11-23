package com.megamaker.codechallenge.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ResponseTestcase {
    private String paramData;
    private String result;

    public String getParamData() {
        return paramData;
    }
}
