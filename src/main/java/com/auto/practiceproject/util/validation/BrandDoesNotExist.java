package com.auto.practiceproject.util.validation;

import com.auto.practiceproject.controller.dto.request.AnnouncementCreateDTO;
import com.auto.practiceproject.service.AutoBrandService;
import lombok.RequiredArgsConstructor;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({PARAMETER, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = BrandDoesNotExist.Validator.class)
public @interface BrandDoesNotExist {

    String message() default "Brand with installed title doesn't exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @RequiredArgsConstructor
    class Validator implements ConstraintValidator<BrandDoesNotExist, String> {

        private final AutoBrandService brandService;

        @Override
        public void initialize(BrandDoesNotExist brandDoesNotExist) {

        }

        @Override
        public boolean isValid(String title, ConstraintValidatorContext context) {
            return brandService.findAutoBrandByTitle(title).isPresent();
        }

    }

}
