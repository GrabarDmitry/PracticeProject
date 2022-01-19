package com.auto.practiceproject.util.validation;

import com.auto.practiceproject.service.UserService;
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
@Constraint(validatedBy = UserExistWithEmail.Validator.class)
public @interface UserExistWithEmail {

    String message() default "user with installed email already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @RequiredArgsConstructor
    class Validator implements ConstraintValidator<UserExistWithEmail, String> {

        private final UserService userService;

        @Override
        public void initialize(UserExistWithEmail constraintAnnotation) {

        }

        @Override
        public boolean isValid(String email, ConstraintValidatorContext context) {
            return email == null || userService.findUserByEmail(email).isEmpty();
        }
    }
}