package com.pavan.app.validations;

import com.pavan.app.annotations.CheckTransactionType;
import com.pavan.app.models.enums.TransactionType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckTransactionTypeValidator implements ConstraintValidator<CheckTransactionType, String> {

    @Override
    public void initialize(CheckTransactionType constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isEmpty()){
            return false;
        }
        for(TransactionType e : TransactionType.values()){
            if(e.name().equals(s)){
                return true;
            }
        }
        return false;
    }
}
