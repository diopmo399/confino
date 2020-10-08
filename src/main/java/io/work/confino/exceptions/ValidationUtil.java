package io.work.confino.exceptions;

import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationUtil {

    public static List<String> fromBindingErrors(Errors errors) {
        return errors
                .getAllErrors()
                .stream()
                .map(item -> item.getDefaultMessage())
                .collect(Collectors.toList());
    }
}
