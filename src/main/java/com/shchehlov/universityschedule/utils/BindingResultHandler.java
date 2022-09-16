package com.shchehlov.universityschedule.utils;

import com.shchehlov.universityschedule.exceptions.InvalidFieldsValueException;
import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class BindingResultHandler {

    public static void handleFieldErrors (BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            String message = bindingResult.getFieldErrors().stream()
                    .map(e -> String.format("%s: %s%n", e.getField(), e.getDefaultMessage()))
                    .collect(Collectors.joining());

            throw new InvalidFieldsValueException(message);
        }
    }
}
