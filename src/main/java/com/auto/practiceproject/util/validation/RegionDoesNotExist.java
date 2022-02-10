package com.auto.practiceproject.util.validation;

import com.auto.practiceproject.service.RegionService;
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
@Constraint(validatedBy = RegionDoesNotExist.Validator.class)
public @interface RegionDoesNotExist {

  String message() default "Region with installed id don't exist";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @RequiredArgsConstructor
  class Validator implements ConstraintValidator<RegionDoesNotExist, Long> {

    private final RegionService regionService;

    @Override
    public void initialize(RegionDoesNotExist regionDoesNotExist) {}

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
      return regionService.findRegion(id).isPresent();
    }
  }
}
