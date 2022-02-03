package com.auto.practiceproject.controller.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component
public class FilterFactory implements ConverterFactory<String, Filter> {

    @Override
    public <T extends Filter> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToFilterConverter(targetType);
    }

    @RequiredArgsConstructor
    private final class StringToFilterConverter<T extends Filter> implements Converter<String, T> {

        private final Class filterType;

        public T convert(String source) {

            if (filterType.equals(AnnouncementFilter.class)) {
                return (T) new AnnouncementFilter(source);
            }

            return null;
        }

    }


}
