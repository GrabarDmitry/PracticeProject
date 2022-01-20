package com.auto.practiceproject.controller.filter;

import com.auto.practiceproject.exception.FilterException;
import com.auto.practiceproject.model.Announcement;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class Filter<T> {

    public abstract Specification<Announcement> filter(String field, String value);

    protected Specification<T> toSpecification(String field, Object value) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(field), value);
        });
    }

    protected Object parser(Map<String, Class> types, String field, String value) {
        try {
            if (types.get(field).equals(Integer.class)) {
                return Integer.valueOf(value);
            } else if (types.get(field).equals(String.class)) {
                return value;
            } else if (types.get(field).equals(Boolean.class)) {
                return Boolean.valueOf(value);
            } else if (types.get(field).equals(Double.class)) {
                return Double.valueOf(value);
            } else if (types.get(field).equals(LocalDate.class)) {
                return LocalDate.parse(value);
            }
            return value;
        } catch (Exception exception) {
            throw new FilterException("Filter parameter is incorrect");
        }
    }

    protected FilterDTO decodeStringFilterToFilterDTO(String filter) {
        try {
            if (filter != null) {
                List<String> params = Arrays.asList(filter.trim().split(".eq"));
                return new FilterDTO(
                        params.get(0),
                        params.get(1).replaceAll("[\\(\\)]", "")
                );
            } else return null;
        } catch (Exception exception) {
            throw new FilterException("Filter parameter is incorrect");
        }
    }

}
