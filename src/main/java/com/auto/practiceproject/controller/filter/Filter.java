package com.auto.practiceproject.controller.filter;

import com.auto.practiceproject.exception.FilterException;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public abstract class Filter<T> {

    private FilterDTO filterDTO;
    private List<String> paths;
    private Class type;
    private String fieldNameInDB;

    protected void setFilter(String filter, Map<String, FilterFieldParams> fieldsType) {
        this.filterDTO = decodeStringFilterToFilterDTO(filter);
        this.paths = fieldsType.get(filterDTO.getField()).getPathParam();
        this.type = fieldsType.get(filterDTO.getField()).getType();
        this.fieldNameInDB = fieldsType.get(filterDTO.getField()).getFieldTitleInDB();
    }

    public Specification<T> toSpecification() {
        return specificationBuilder(paths);
    }

    protected abstract void setParam(String filter);

    protected Object parser(Class type, String value) {
        try {
            if (type.equals(Integer.class)) {
                return Integer.valueOf(value);
            } else if (type.equals(String.class)) {
                return value;
            } else if (type.equals(Boolean.class)) {
                return Boolean.valueOf(value);
            } else if (type.equals(Double.class)) {
                return Double.valueOf(value);
            } else if (type.equals(LocalDate.class)) {
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
                String[] parts = filter.trim().split("\\.", 3);
                return new FilterDTO(
                        parts[0],
                        OperationType.valueOf(parts[1]),
                        parts[2]
                );
            } else return null;
        } catch (Exception exception) {
            throw new FilterException("Filter parameter is incorrect");
        }
    }

    private Specification<T> specificationBuilder(List<String> pathParam) {

        switch (pathParam.size()) {
            case (0):
                return toSpecificationWithPath();
            case (1):
                return toSpecificationWithPath(pathParam.get(0));
            case (2):
                return toSpecificationWithPath(pathParam.get(0), pathParam.get(1));
            case (3):
                return toSpecificationWithPath(pathParam.get(0), pathParam.get(1), pathParam.get(2));
        }
        return null;
    }

    private Specification<T> toSpecificationWithPath() {
        switch (filterDTO.getOperationType()) {
            case eq:
                return toSpecificationWithPathEquals();
        }
        return null;
    }

    private Specification<T> toSpecificationWithPath(String pathParam1) {
        switch (filterDTO.getOperationType()) {
            case eq:
                return toSpecificationWithPathEquals(pathParam1);
        }
        return null;
    }

    private Specification<T> toSpecificationWithPath(String pathParam1, String pathParam2) {
        switch (filterDTO.getOperationType()) {
            case eq:
                return toSpecificationWithPathEquals(pathParam1, pathParam2);
        }
        return null;
    }

    private Specification<T> toSpecificationWithPath(String pathParam1, String pathParam2, String pathParam3) {
        switch (filterDTO.getOperationType()) {
            case eq:
                return toSpecificationWithPathEquals(pathParam1, pathParam2, pathParam3);
        }
        return null;
    }

    private Specification<T> toSpecificationWithPathEquals() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(fieldNameInDB), parser(type, filterDTO.getValue()));
        };
    }

    private Specification<T> toSpecificationWithPathEquals(String pathParam1) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join(pathParam1, JoinType.LEFT)
                    .get(fieldNameInDB), parser(type, filterDTO.getValue()));
        };
    }

    private Specification<T> toSpecificationWithPathEquals(String pathParam1, String pathParam2) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join(pathParam1, JoinType.LEFT)
                    .join(pathParam2, JoinType.LEFT)
                    .get(fieldNameInDB), parser(type, filterDTO.getValue()));
        };
    }

    private Specification<T> toSpecificationWithPathEquals(String pathParam1, String pathParam2, String pathParam3) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join(pathParam1, JoinType.LEFT)
                    .join(pathParam2, JoinType.LEFT)
                    .join(pathParam3, JoinType.LEFT)
                    .get(fieldNameInDB), parser(type, filterDTO.getValue()));
        };
    }

}
