package com.auto.practiceproject.controller.filter;

import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//TODO
public interface FilteredService {

    default <T> Specification<T> applyFilter(Filter filterClass, List<String> filters) {

        if (filters == null) {
            filters = Collections.emptyList();
        }

        Specification<T> result = null;

        for (int i = 0; i < filters.size(); i++) {

            String field = filterClass.decodeStringFilterToFilterDTO(filters.get(i)).getField();
            String value = filterClass.decodeStringFilterToFilterDTO(filters.get(i)).getValue();

            if (result == null) {
                result = Specification.where(
                        filterClass.filter(field, value));
            } else {
                result = result.and(filterClass.filter(field, value));
            }
        }

        return result;

    }

    default List<String> decodeStringFilter(String filter) {
        if (filter != null) {
            return Arrays.asList(filter.trim().split(";"));
        } else return Collections.emptyList();
    }

}
