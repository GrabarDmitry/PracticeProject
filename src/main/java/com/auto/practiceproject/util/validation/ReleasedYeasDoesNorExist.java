package com.auto.practiceproject.util.validation;


import com.auto.practiceproject.service.AutoReleasedYearService;
import lombok.RequiredArgsConstructor;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({PARAMETER, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ReleasedYeasDoesNorExist.Validator.class)
public @interface ReleasedYeasDoesNorExist {

    String message() default "Auto with installed released year don't exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @RequiredArgsConstructor
    class Validator implements ConstraintValidator<ReleasedYeasDoesNorExist, LocalDate> {

        private final AutoReleasedYearService autoReleasedYearService;

        @Override
        public void initialize(ReleasedYeasDoesNorExist releasedYeasDoesNorExist) {

        }

        @Override
        public boolean isValid(LocalDate localDate, ConstraintValidatorContext context) {
            return autoReleasedYearService.findAutoReleasedYearByReleased(localDate)
                    .isPresent();
        }

    }
}
