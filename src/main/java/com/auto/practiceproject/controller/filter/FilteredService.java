package com.auto.practiceproject.controller.filter;

import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface FilteredService {

    default <T> Specification<T> applyFilter(Filter filterClass, List<String> filters, List<Specification> additions) {

        if (filters == null) {
            filters = Collections.emptyList();
        }

        Specification<T> result = null;

        for (String filter : filters) {
            filterClass.setParam(filter);
            if (result == null) {
                result = Specification.where(
                        filterClass.toSpecification());
            } else {
                result = result.and(filterClass.toSpecification());
            }
        }

        if (additions != null) {
            for (Specification additional : additions) {
                if (result == null) {
                    result = Specification.where(additional);
                } else {
                    result = result.and(additional);
                }
            }
        }

        return result;

    }

    default <T> Specification<T> applyFilter(Filter filterClass, List<String> filters) {
        return applyFilter(filterClass, filters, null);
    }

    default List<String> decodeStringFilter(String filter) {
        if (filter != null) {
            return Arrays.asList(filter.trim().split(";"));
        } else return Collections.emptyList();
    }

}
