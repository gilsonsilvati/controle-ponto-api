package com.controleponto.api.modelmapper;

import lombok.Builder;

import java.time.LocalTime;
import java.util.List;

@Builder
public class RelatorioModel {

    private FuncionarioModel funcionario;
    private LocalTime horasTrabalhadas;
    private LocalTime horasExcedentes;
    private LocalTime horasDevidas;
    private List<LocalTime> horarios;

}
