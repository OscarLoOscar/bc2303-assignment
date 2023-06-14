package com.codewave.project.crypto.coingecko.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.codewave.project.crypto.coingecko.config.SimplePricePathVariablesValiator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = SimplePricePathVariablesValiator.class)
@Target(ElementType.PARAMETER) // method's parameter
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSimplePrice {
    String message() default "Invalid path variables";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String customMessage() default "";
}

