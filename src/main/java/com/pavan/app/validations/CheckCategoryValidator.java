package com.pavan.app.validations;

import com.pavan.app.annotations.CheckCategory;
import com.pavan.app.models.enums.Category;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckCategoryValidator implements ConstraintValidator<CheckCategory, String> {

    @Override
    public void initialize(CheckCategory constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isEmpty()){
            return false;
        }
        for(Category e : Category.values()){
            if(e.name().equals(s)){
                return true;
            }
        }
        return false;
    }
}
