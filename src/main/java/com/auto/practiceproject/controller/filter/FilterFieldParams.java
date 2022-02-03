package com.auto.practiceproject.controller.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterFieldParams {

    private String fieldTitleInDB;
    private Class type;
    private List<String> pathParam;

}
