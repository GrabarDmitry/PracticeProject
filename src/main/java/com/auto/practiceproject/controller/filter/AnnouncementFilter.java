package com.auto.practiceproject.controller.filter;

import com.auto.practiceproject.model.Announcement;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.JoinType;
import java.util.Date;
import java.util.Map;

import static java.util.Map.entry;

//TODO
@Component
public class AnnouncementFilter extends Filter<Announcement> {

    private final String DB_FIELD_TITLE = "title";
    private final String DB_FIELD_TYPE = "type";

    private Map<String, Class> fieldsType = Map.ofEntries(
            entry("price", Double.class),
            entry("brand", String.class),
            entry("model", String.class),
            entry("releasedYear", Date.class),
            entry("mileage", Integer.class),
            entry("engineType", String.class),
            entry("engineCapacity", Integer.class),
            entry("transmission", String.class),
            entry("region", String.class),
            entry("isExchange", Boolean.class),
            entry("customsDuty", Double.class)
    );

    @Override
    public Specification<Announcement> filter(String field, String value) {
        if (field.equals("price") || field.equals("isExchange") || field.equals("customsDuty")) {
            return toSpecification(field, parser(fieldsType, field, value));
        } else if (field.equals("mileage") || field.equals("engineCapacity")) {
            return filterByAnnouncementAuto(field, parser(fieldsType, field, value));
        } else if (field.equals("brand")) {
            return filterByAnnouncementAutoBrand(DB_FIELD_TITLE, parser(fieldsType, DB_FIELD_TITLE, value));
        } else if (field.equals("releasedYear")) {
            return filterByAnnouncementAutoReleaseYear(field, parser(fieldsType, field, value));
        } else if (field.equals("model")) {
            return filterByAnnouncementAutoModel(DB_FIELD_TITLE, parser(fieldsType, DB_FIELD_TITLE, value));
        } else if (field.equals("engineType")) {
            return filterByAnnouncementAutoEngineType(DB_FIELD_TYPE, parser(fieldsType, DB_FIELD_TYPE, value));
        } else if (field.equals("transmission")) {
            return filterByAnnouncementAutoTransmissionType(DB_FIELD_TYPE, parser(fieldsType, DB_FIELD_TYPE, value));
        } else if (field.equals("region")) {
            return filterByAnnouncementRegion(DB_FIELD_TITLE, parser(fieldsType, DB_FIELD_TITLE, value));
        }
        return null;
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
                    join("autoReleased").get(field), value);
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

}

