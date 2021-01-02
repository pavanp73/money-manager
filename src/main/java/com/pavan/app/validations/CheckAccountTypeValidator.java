package com.pavan.app.validations;

import com.pavan.app.annotations.CheckAccountType;
import com.pavan.app.models.enums.AccountType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckAccountTypeValidator implements ConstraintValidator<CheckAccountType, String> {

    @Override
    public void initialize(CheckAccountType constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isEmpty()){
            return false;
        }
        for (AccountType e : AccountType.values()){
            if(e.name().equals(s)){
                return true;
            }
        }
        return false;
    }
}
