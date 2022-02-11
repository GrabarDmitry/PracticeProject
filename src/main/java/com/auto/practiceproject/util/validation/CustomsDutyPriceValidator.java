package com.auto.practiceproject.util.validation;

import com.auto.practiceproject.model.Region;
import com.auto.practiceproject.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = CustomsDutyPriceValidator.Validator.class)
public @interface CustomsDutyPriceValidator {

  String field() default "customsDuty";

  String message() default "";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @RequiredArgsConstructor
  class Validator implements ConstraintValidator<CustomsDutyPriceValidator, Object> {

    private final RegionService regionService;
    private static final SpelExpressionParser PARSER = new SpelExpressionParser();

    @Override
    public void initialize(CustomsDutyPriceValidator customsDutyPriceValidator) {}

    @Override
    public boolean isValid(Object dto, ConstraintValidatorContext context) {
      context.disableDefaultConstraintViolation();
      Long regionId = (Long) PARSER.parseExpression("regionId").getValue(dto);
      Double price = (Double) PARSER.parseExpression("customsDuty").getValue(dto);
      Region region = regionService.findRegion(regionId).orElse(null);

      if (region != null && region.getTitle().equals("Abroad") && price.equals(0.0)) {
        context
            .buildConstraintViolationWithTemplate(
                "For a car from abroad, the customs price must be indicated")
            .addConstraintViolation();
        return false;
      } else if (region != null && !(region.getTitle().equals("Abroad")) && price > 0.0) {
        context
            .buildConstraintViolationWithTemplate(
                "For a car from a country, the customs price is not indicated")
            .addConstraintViolation();
        return false;
      }
      return true;
    }
  }
}
