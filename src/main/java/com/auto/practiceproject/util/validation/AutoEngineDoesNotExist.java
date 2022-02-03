package com.auto.practiceproject.util.validation;

import com.auto.practiceproject.service.AutoEngineService;
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
@Constraint(validatedBy = AutoEngineDoesNotExist.Validator.class)
public @interface AutoEngineDoesNotExist {

    String message() default "Auto engine with installed id don't exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @RequiredArgsConstructor
    class Validator implements ConstraintValidator<AutoEngineDoesNotExist, Long> {

        private final AutoEngineService autoEngineService;

        @Override
        public void initialize(AutoEngineDoesNotExist autoEngineDoesNotExist) {
        }

        @Override
        public boolean isValid(Long id, ConstraintValidatorContext context) {
            return autoEngineService.findAutoEngine(id)
                    .isPresent();
        }

    }

}
