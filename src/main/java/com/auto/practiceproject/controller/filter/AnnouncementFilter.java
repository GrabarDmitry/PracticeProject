package com.auto.practiceproject.controller.filter;

import com.auto.practiceproject.exception.FilterException;
import com.auto.practiceproject.model.Announcement;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.JoinType;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class AnnouncementFilter extends Filter<Announcement> {

    private final String DB_FIELD_TITLE = "title";
    private final String DB_FIELD_TYPE = "type";
    private final String DB_FIELD_Id = "id";

    private Map<String, Class> fieldsType = new HashMap<>() {{
        put("price", Double.class);
        put("brand", String.class);
        put("model", String.class);
        put("releasedYear", LocalDate.class);
        put("mileage", Integer.class);
        put("engineType", String.class);
        put("engineCapacity", Integer.class);
        put("transmission", String.class);
        put("region", String.class);
        put("isExchange", Boolean.class);
        put("isModeration", Boolean.class);
        put("customsDuty", Double.class);
        put("userId", Long.class);
    }};

    @Override
    public Specification<Announcement> filter(String field, String value) {
        try {
            if (field.equals("price") || field.equals("isExchange")
                    || field.equals("customsDuty") || field.equals("isModeration")) {
                return toSpecification(field, parser(fieldsType, field, value));
            } else if (field.equals("mileage") || field.equals("engineCapacity")) {
                return filterByAnnouncementAuto(field, parser(fieldsType, field, value));
            } else if (field.equals("brand")) {
                return filterByAnnouncementAutoBrand(DB_FIELD_TITLE, parser(fieldsType, field, value));
            } else if (field.equals("releasedYear")) {
                return filterByAnnouncementAutoReleaseYear(field, parser(fieldsType, field, value));
            } else if (field.equals("model")) {
                return filterByAnnouncementAutoModel(DB_FIELD_TITLE, parser(fieldsType, field, value));
            } else if (field.equals("engineType")) {
                return filterByAnnouncementAutoEngineType(DB_FIELD_TYPE, parser(fieldsType, field, value));
            } else if (field.equals("transmission")) {
                return filterByAnnouncementAutoTransmissionType(DB_FIELD_TYPE, parser(fieldsType, field, value));
            } else if (field.equals("region")) {
                return filterByAnnouncementRegion(DB_FIELD_TITLE, parser(fieldsType, field, value));
            } else if (field.equals("userId")) {
                return filterByAnnouncementUser(DB_FIELD_Id, parser(fieldsType, field, value));
            }
            return null;
        } catch (Exception exception) {
            throw new FilterException("Filter parameter is incorrect");
        }
    }

    private Specification<Announcement> filterByAnnouncementAutoBrand(String field, Object value) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join("auto", JoinType.LEFT).
                    join("autoModel", JoinType.LEFT).
                    join("autoBrand").get(field), value);
        });
    }

    private Specification<Announcement> filterByAnnouncementAutoReleaseYear(String field, Object value) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join("auto", JoinType.LEFT).
                    join("autoModel", JoinType.LEFT).
                    join("autoReleasedYear").get(field), value);
        });
    }

    private Specification<Announcement> filterByAnnouncementAutoModel(String field, Object value) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join("auto", JoinType.LEFT).
                    join("autoModel", JoinType.LEFT).get(field), value);
        });
    }

    private Specification<Announcement> filterByAnnouncementAutoEngineType(String field, Object value) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join("auto", JoinType.LEFT).
                    join("autoEngine", JoinType.LEFT).get(field), value);
        });
    }

    private Specification<Announcement> filterByAnnouncementAutoTransmissionType(String field, Object value) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join("auto", JoinType.LEFT).
                    join("autoTransmission", JoinType.LEFT).get(field), value);
        });
    }

    private Specification<Announcement> filterByAnnouncementAuto(String field, Object value) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join("auto", JoinType.LEFT).get(field), value);
        });
    }

    private Specification<Announcement> filterByAnnouncementRegion(String field, Object value) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join("region", JoinType.LEFT).get(field), value);
        });
    }

    private Specification<Announcement> filterByAnnouncementUser(String field, Object value) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.join("user", JoinType.LEFT).get(field), value);
        });
    }

}

