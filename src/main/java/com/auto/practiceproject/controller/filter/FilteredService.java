package com.auto.practiceproject.controller.filter;

import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//TODO
public interface FilteredService {

    default <T> Specification<T> applyFilter(Filter filter, List<String> filters) {

        if (filters.isEmpty()) {
            return null;
        }

        return filter.filter(filters.get(0),filters.get(1));

    }

    default List<String> decodeStringFilter(String filter) {
        if (filter != null) {
            List<String> params = Arrays.asList(filter.trim().split(".eq"));
            return List.of(
                    params.get(0),
                    params.get(1).replaceAll("[\\(\\)]", "")
            );
        } else return Collections.emptyList();
    }

}
