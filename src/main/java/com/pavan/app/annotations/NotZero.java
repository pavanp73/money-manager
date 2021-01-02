package com.pavan.app.annotations;

import com.pavan.app.validations.NotZeroValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = NotZeroValidator.class)
@Documented
public @interface NotZero {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
