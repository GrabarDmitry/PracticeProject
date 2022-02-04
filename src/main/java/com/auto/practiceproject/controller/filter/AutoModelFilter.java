package com.auto.practiceproject.controller.filter;

import com.auto.practiceproject.model.AutoModel;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoModelFilter extends Filter<AutoModel> {

    private static final Map<String, FilterFieldParams> fieldsType = new HashMap<>() {{
        put("title", new FilterFieldParams("title", String.class, Collections.emptyList()));
        put("autoBrandId", new FilterFieldParams("id", Long.class, List.of("autoBrand")));
        put("autoReleasedYearId", new FilterFieldParams("id", Long.class, List.of("autoReleasedYear")));
    }};

    public AutoModelFilter(String filter) {
        super(filter, fieldsType);
    }

}
