package com.pavan.app.validations;

import com.pavan.app.annotations.CheckPaymentMode;
import com.pavan.app.models.enums.PaymentMode;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckPaymentModeValidator implements ConstraintValidator<CheckPaymentMode, String> {

    @Override
    public void initialize(CheckPaymentMode constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isEmpty()){
            return false;
        }
        for (PaymentMode e : PaymentMode.values()){
            if(e.name().equals(s)){
                return true;
            }
        }
        return false;
    }
}
