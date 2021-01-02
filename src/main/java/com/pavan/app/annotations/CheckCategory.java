package com.pavan.app.annotations;

import com.pavan.app.validations.CheckCategoryValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckCategoryValidator.class)
@Documented
public @interface CheckCategory {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
