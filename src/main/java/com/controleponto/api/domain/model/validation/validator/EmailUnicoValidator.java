package com.controleponto.api.domain.model.validation.validator;

import com.controleponto.api.domain.model.validation.EmailUnico;
import com.controleponto.api.domain.repository.Funcionarios;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, String> {

    @Autowired
    private Funcionarios funcionarios;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return funcionarios.findByEmailIgnoreCase(email).isEmpty();
    }

}
