package com.controleponto.api.domain.model;

import com.controleponto.api.domain.model.base.EntidadeBase;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Funcionario extends EntidadeBase {

    private String nome;
    private String email;
    private String cpf;

    @ManyToMany
    @JoinTable(name = "funcionario_projeto",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name = "projeto_id")
    )
    private List<Projeto> projetos;

    @OneToMany(mappedBy = "funcionario")
    private List<Lancamento> lancamentos;

    @PrePersist @PreUpdate
    private void prePersistPreUpdate() {
        if (StringUtils.isNotBlank(cpf))
            cpf = removerFormatacao();
    }

    @PostUpdate
    private void postPersistPostUpdate() {
        if (StringUtils.isNotBlank(cpf))
            cpf = formatar();
    }

    @PostLoad
    private void postLoad() {
        if (StringUtils.isNotBlank(cpf))
            cpf = formatar();
    }

    public String removerFormatacao() {
        return cpf.replaceAll("\\.|-|/", "");
    }

    public String formatar() {
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
    }

}
