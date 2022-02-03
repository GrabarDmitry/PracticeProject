package com.auto.practiceproject.util.validation;

import com.auto.practiceproject.service.AutoTransmissionService;
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
@Constraint(validatedBy = AutoTransmissionDoesNotExist.Validator.class)
public @interface AutoTransmissionDoesNotExist {

    String message() default "Auto transmission with installed id don't exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @RequiredArgsConstructor
    class Validator implements ConstraintValidator<AutoTransmissionDoesNotExist, Long> {

        private final AutoTransmissionService autoTransmissionService;

        @Override
        public void initialize(AutoTransmissionDoesNotExist autoTransmissionDoesNotExist) {
        }

        @Override
        public boolean isValid(Long id, ConstraintValidatorContext context) {
            return autoTransmissionService.findAutoTransmission(id)
                    .isPresent();
        }

    }
}
