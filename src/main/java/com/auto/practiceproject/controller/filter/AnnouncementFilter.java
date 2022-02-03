package com.auto.practiceproject.controller.filter;

import com.auto.practiceproject.model.Announcement;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AnnouncementFilter extends Filter<Announcement> {
    private Map<String, FilterFieldParams> fieldsType = new HashMap<>() {{
        put("price", new FilterFieldParams("price", Double.class, Collections.emptyList()));
        put("autoBrandId", new FilterFieldParams("id", Long.class, List.of("auto", "autoModel", "autoBrand")));
        put("autoModelId", new FilterFieldParams("id", Long.class, List.of("auto", "autoModel")));
        put("autoReleasedYearId", new FilterFieldParams("id", Long.class, List.of("auto", "autoModel", "autoReleasedYear")));
        put("mileage", new FilterFieldParams("mileage", Integer.class, List.of("auto")));
        put("autoEngineId", new FilterFieldParams("id", Long.class, List.of("auto", "autoEngine")));
        put("engineCapacity", new FilterFieldParams("engineCapacity", Integer.class, List.of("auto")));
        put("autoTransmissionId", new FilterFieldParams("id", Long.class, List.of("auto", "autoTransmission")));
        put("regionId", new FilterFieldParams("id", Long.class, List.of("region")));
        put("isExchange", new FilterFieldParams("isExchange", Boolean.class, Collections.emptyList()));
        put("isModeration", new FilterFieldParams("isModeration", Boolean.class, Collections.emptyList()));
        put("customsDuty", new FilterFieldParams("customsDuty", Double.class, Collections.emptyList()));
        put("userId", new FilterFieldParams("id", Long.class, List.of("user")));
    }};

    @Override
    protected void setParam(String filter) {
        setFilter(filter, fieldsType);
    }

}

