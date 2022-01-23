package com.auto.practiceproject.util.validation;


import com.auto.practiceproject.service.AnnouncementService;
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
@Constraint(validatedBy = AnnouncementDoesNotExist.Validator.class)
public @interface AnnouncementDoesNotExist {

    String message() default "Announcement with installed announcement id doesn't exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @RequiredArgsConstructor
    class Validator implements ConstraintValidator<AnnouncementDoesNotExist, Long> {

        private final AnnouncementService announcementService;

        @Override
        public void initialize(AnnouncementDoesNotExist announcementDoesNotExist) {

        }

        @Override
        public boolean isValid(Long id, ConstraintValidatorContext context) {
            return announcementService.findAnnouncement(id).isPresent();
        }

    }
}
