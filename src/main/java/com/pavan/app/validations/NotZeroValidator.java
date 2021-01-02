package com.pavan.app.validations;

import com.pavan.app.annotations.NotZero;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotZeroValidator implements ConstraintValidator<NotZero, Double> {

    @Override
    public void initialize(com.pavan.app.annotations.NotZero constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {
        return aDouble != null && aDouble.compareTo(0d) != 0;
    }
}
