package com.auto.practiceproject.controller.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FilterDTO {
    private String field;
    private String value;
}
