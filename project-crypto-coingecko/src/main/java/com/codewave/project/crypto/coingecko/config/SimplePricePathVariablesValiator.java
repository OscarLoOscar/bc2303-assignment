package com.codewave.project.crypto.coingecko.config;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.codewave.project.crypto.coingecko.annotation.ValidSimplePrice;
import com.codewave.project.crypto.coingecko.infra.enums.Currency;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class SimplePricePathVariablesValiator implements ConstraintValidator<ValidSimplePrice, Map<String, String>> {
  private String customMessage;

  @Override
  public void initialize(ValidSimplePrice constraintAnnotation) {
    // Initialization logic, if needed
    customMessage = constraintAnnotation.customMessage();
  }

  @Override
  public boolean isValid(Map<String, String> pathVariables, ConstraintValidatorContext context) {
    // Validation logic here
    if (pathVariables.get("fromCurr") == null || pathVariables.get("toCurr") == null) {
      addMessage(context, this.customMessage);
      return false;
    }
    if (Currency.valueOf(pathVariables.get("fromCurr")).isCrypto() // ETH
        && Currency.valueOf(pathVariables.get("toCurr")).isCrypto() // ETH
        || !Currency.valueOf(pathVariables.get("fromCurr")).isCrypto() // USD
            && !Currency.valueOf(pathVariables.get("toCurr")).isCrypto()) { // USD
      addMessage(context, this.customMessage);
      return false;
    }
    return true;
  }

  private static void addMessage(ConstraintValidatorContext context, String message) {
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
  }
}
