package com.auto.practiceproject.util.validation;

import com.auto.practiceproject.service.AutoBrandService;
import com.auto.practiceproject.service.AutoModelService;
import com.auto.practiceproject.service.AutoReleasedYearService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.LocalDate;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = ModelDoesNotExistWithBrandAndYear.Validator.class)
public @interface ModelDoesNotExistWithBrandAndYear {

    String field() default "";

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @RequiredArgsConstructor
    class Validator implements ConstraintValidator<ModelDoesNotExistWithBrandAndYear, Object> {

        private static final SpelExpressionParser PARSER = new SpelExpressionParser();
        private final AutoModelService service;
        private final AutoReleasedYearService autoReleasedYearService;
        private final AutoBrandService autoBrandService;

        @Override
        public void initialize(ModelDoesNotExistWithBrandAndYear constraintAnnotation) {
        }

        @Override
        public boolean isValid(Object dto, ConstraintValidatorContext context) {
            String model = (String) PARSER.parseExpression("model").getValue(dto);
            String brand = (String) PARSER.parseExpression("brand").getValue(dto);
            LocalDate releasedYear = (LocalDate) PARSER.parseExpression("releasedYear").getValue(dto);

            return service.findAutoModelByTitleAndAutoBrandAndAutoReleasedYear(
                    model,
                    autoBrandService.findAutoBrandByTitle(brand)
                            .orElse(null),
                    autoReleasedYearService.findAutoReleasedYearByReleased(releasedYear)
                            .orElse(null)
            ).isPresent();

        }

    }
}
