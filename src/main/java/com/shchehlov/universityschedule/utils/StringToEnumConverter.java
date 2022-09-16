package com.shchehlov.universityschedule.utils;


import com.shchehlov.universityschedule.model.attributes.WeekDay;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToEnumConverter implements Converter<String, WeekDay> {

    @Override
    public WeekDay convert(String source) {
        return WeekDay.valueOf(source.toUpperCase());
    }
}
