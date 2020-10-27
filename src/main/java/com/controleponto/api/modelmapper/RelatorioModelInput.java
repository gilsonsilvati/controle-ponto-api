package com.controleponto.api.modelmapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RelatorioModelInput {

    @NotNull(message = "Data de início é obrigatória")
    private LocalDate inicio;

    @NotNull(message = "Data de fim é obrigatória")
    private LocalDate fim;

}
