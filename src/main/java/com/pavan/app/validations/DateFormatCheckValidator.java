package com.pavan.app.validations;

import com.pavan.app.annotations.DateFormatCheck;
import com.pavan.app.services.util.DateUtility;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class DateFormatCheckValidator implements ConstraintValidator<DateFormatCheck, String> {

    @Override
    public void initialize(DateFormatCheck constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null || s.isEmpty()){
            return false;
        }
        boolean valid = true;
        try{
            DateUtility.convertToDate(s);
        } catch (DateTimeParseException e){
            valid = false;
        }
        return valid;
    }
}
