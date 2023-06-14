package com.codewave.project.crypto.polygon.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.codewave.project.crypto.polygon.config.PreviousClosePathVariablesValiator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = PreviousClosePathVariablesValiator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPreviousClose {
    String message() default "Invalid path variables";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String customMessage() default "";
}

