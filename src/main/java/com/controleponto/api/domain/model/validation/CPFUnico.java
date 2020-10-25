package com.controleponto.api.domain.model.validation;

import com.controleponto.api.domain.model.validation.validator.CPFUnicoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CPFUnicoValidator.class)
public @interface CPFUnico {

    String message() default "Já existe um funcionário cadastrado com este cpf";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
