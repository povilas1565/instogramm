package com.example.demo.annotations;
import com.example.demo.validators.EmailValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {
    String message() default "Invalid email";

    Class<?>[] groups() default {};
Class<? extends Payload>[] payload() default{};
}