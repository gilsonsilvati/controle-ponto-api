package com.controleponto.api.domain.model.validation;

import com.controleponto.api.domain.model.validation.validator.EmailUnicoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailUnicoValidator.class)
public @interface EmailUnico {

    String message() default "Já existe um funcionário cadastrado com este email";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
