package com.auto.practiceproject.controller.filter;

import com.auto.practiceproject.exception.FilterException;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public abstract class Filter<T> {

  private FilterDTO filterDTO;
  private List<String> paths;
  private Class type;
  private String fieldNameInDB;

  public Filter(String filter, Map<String, FilterFieldParams> fieldsType) {
    this.filterDTO = decodeStringFilterToFilterDTO(filter);
    initParam(fieldsType);
  }

  public Specification<T> toSpecification() {
    try {
      return (root, query, criteriaBuilder) -> {
        switch (filterDTO.getOperationType()) {
          case eq:
            {
              return criteriaBuilder.equal(
                  pathBuilder(paths, root), parser(type, filterDTO.getValue()));
            }
          case less:
            {
              return criteriaBuilder.lessThan(
                  pathBuilder(paths, root), (Comparable) parser(type, filterDTO.getValue()));
            }
          case more:
            {
              return criteriaBuilder.greaterThan(
                  pathBuilder(paths, root), (Comparable) parser(type, filterDTO.getValue()));
            }
        }
        return null;
      };
    } catch (Exception exception) {
      throw new FilterException("Filter parameter is incorrect");
    }
  }

  protected Object parser(Class type, String value) {
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
  }

  protected FilterDTO decodeStringFilterToFilterDTO(String filter) {
    try {
      if (filter != null) {
        String[] parts = filter.trim().split("\\.", 3);
        return new FilterDTO(parts[0], OperationType.valueOf(parts[1]), parts[2]);
      } else return null;
    } catch (Exception exception) {
      throw new FilterException("Filter parameter is incorrect");
    }
  }

  private Path pathBuilder(List<String> paths, Root root) {
    switch (paths.size()) {
      case (0):
        {
          return root.get(fieldNameInDB);
        }
      case (1):
        {
          return root.join(paths.get(0), JoinType.LEFT).get(fieldNameInDB);
        }
      case (2):
        {
          return root.join(paths.get(0), JoinType.LEFT)
              .join(paths.get(1), JoinType.LEFT)
              .get(fieldNameInDB);
        }
      case (3):
        {
          return root.join(paths.get(0), JoinType.LEFT)
              .join(paths.get(1), JoinType.LEFT)
              .join(paths.get(2), JoinType.LEFT)
              .get(fieldNameInDB);
        }
    }
    return root.get(fieldNameInDB);
  }

  private void initParam(Map<String, FilterFieldParams> fieldsType) {
    try {
      this.paths = fieldsType.get(filterDTO.getField()).getPathParam();
      this.type = fieldsType.get(filterDTO.getField()).getType();
      this.fieldNameInDB = fieldsType.get(filterDTO.getField()).getFieldTitleInDB();
    } catch (Exception exception) {
      throw new FilterException("Filter parameter is incorrect");
    }
  }
}
