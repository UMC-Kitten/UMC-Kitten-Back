package umc.kittenback.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import umc.kittenback.domain.enums.PostType;
import umc.kittenback.validation.annotation.ExistPostType;

public class PostTypeValidator implements ConstraintValidator<ExistPostType, PostType> {


    @Override
    public void initialize(ExistPostType constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PostType value, ConstraintValidatorContext context) {
        boolean isValid = false;
//
//        if(PostType.valueOf(value.name())){
//            return true;
//        }else{
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate(ErrorStatus.POSTTYPE_NOT_FOUND.toString()).addConstraintViolation();
//            return false;
//        }
        return false;
    }
}
