package com.controleponto.api.domain.model.validation.validator;

import com.controleponto.api.domain.model.validation.CPFUnico;
import com.controleponto.api.domain.repository.Funcionarios;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CPFUnicoValidator implements ConstraintValidator<CPFUnico, String> {

    @Autowired
    private Funcionarios funcionarios;

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext validatorContext) {
        return funcionarios.findByCpf(cpf).isEmpty();
    }

}
