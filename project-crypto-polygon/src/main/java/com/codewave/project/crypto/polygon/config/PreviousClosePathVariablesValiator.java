package com.codewave.project.crypto.polygon.config;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.codewave.project.crypto.polygon.infra.enums.Currency;
import com.codewave.project.crypto.polygon.annotation.ValidPreviousClose;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class PreviousClosePathVariablesValiator
    implements ConstraintValidator<ValidPreviousClose, Map<String, String>> {
  private String customMessage;

  @Override
  public void initialize(ValidPreviousClose constraintAnnotation) {
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
    if (Currency.valueOf(pathVariables.get("fromCurr")).isCrypto()
        && Currency.valueOf(pathVariables.get("toCurr")).isCrypto()
        || !Currency.valueOf(pathVariables.get("fromCurr")).isCrypto()
            && !Currency.valueOf(pathVariables.get("toCurr")).isCrypto()) {
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
