package com.auto.practiceproject.controller.filter;

import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface FilteredService {

    default <T> Specification<T> applyFilter(List<? extends Filter> filters, List<Specification> additions) {

        if (filters == null) {
            filters = Collections.emptyList();
        }

        Specification<T> result = null;

        for (Filter filter : filters) {
            if (result == null) {
                result = Specification.where(
                        filter.toSpecification());
            } else {
                result = result.and(filter.toSpecification());
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

    default <T> Specification<T> applyFilter(List<? extends Filter> filters) {
        return applyFilter(filters, null);
    }

    default List<String> decodeStringFilter(String filter) {
        if (filter != null) {
            return Arrays.asList(filter.trim().split(";"));
        } else return Collections.emptyList();
    }

}
