package com.pavan.app.annotations;

import com.pavan.app.validations.CheckTransactionTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckTransactionTypeValidator.class)
@Documented
public @interface CheckTransactionType {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
