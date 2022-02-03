package com.auto.practiceproject.util.validation;

import com.auto.practiceproject.service.AutoModelService;
import lombok.RequiredArgsConstructor;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({PARAMETER, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = AutoModelDoesNotExist.Validator.class)
public @interface AutoModelDoesNotExist {

    String message() default "Models with installed id don't exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @RequiredArgsConstructor
    class Validator implements ConstraintValidator<AutoModelDoesNotExist, Long> {

        private final AutoModelService autoModelService;

        @Override
        public void initialize(AutoModelDoesNotExist modelDoesNotExist) {}

        @Override
        public boolean isValid(Long id, ConstraintValidatorContext context) {
            return autoModelService.findAutoModel(id)
                    .isPresent();
        }

    }

}
