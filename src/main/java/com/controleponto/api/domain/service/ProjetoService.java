package com.controleponto.api.domain.service;

import com.controleponto.api.domain.exception.EntidadeNaoEncontradaException;
import com.controleponto.api.domain.exception.NegocioException;
import com.controleponto.api.domain.model.Projeto;
import com.controleponto.api.domain.repository.Projetos;
import com.controleponto.api.modelmapper.ProjetoModel;
import com.controleponto.api.modelmapper.ProjetoModelInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Projetos projetos;

    public List<ProjetoModel> listar() {
        return toCollectionModel(projetos.findAll());
    }

    public ProjetoModel porId(Integer id) {
        Projeto projeto = buscarPorId(id);
        return toModel(projeto);
    }

    public ProjetoModel porNome(String nome) {
        Projeto projeto = projetos.findByNomeIgnoreCaseAndAtivo(nome, true)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Projeto não localizado."));

        return toModel(projeto);
    }

    public Projeto salvar(ProjetoModelInput projetoModelInput) {
        Optional<Projeto> projetoExistente = projetos.findByNomeIgnoreCaseAndAtivo(projetoModelInput.getNome(), true);

        if (projetoExistente.isPresent())
            throw new NegocioException("Já existe um projeto cadastrado com este nome.");

        Projeto projeto = toEntity(projetoModelInput);
        projeto = projetos.save(projeto);

        return projeto;
    }

    public ProjetoModel atualizar(Integer id, ProjetoModelInput projetoModelInput) {
        Projeto projeto = buscarPorId(id);
        Optional<Projeto> projetoExistente = projetos.findByNomeIgnoreCaseAndAtivo(projetoModelInput.getNome(), true);

        if (projetoExistente.isPresent() && projetoExistente.get().getId() != projeto.getId())
            throw new NegocioException("Já existe um projeto cadastrado com este nome.");

        BeanUtils.copyProperties(projetoModelInput, projeto, "id");
        projetos.save(projeto);

        return toModel(projeto);
    }

    public void atualizarPropriedadeAtivo(Integer id, Boolean ativo) {
        Projeto projeto = buscarPorId(id);
        projeto.setAtivo(ativo);
        projetos.save(projeto);
    }

    private Projeto buscarPorId(Integer id) {
        return projetos.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Projeto não localizado."));
    }

    private List<ProjetoModel> toCollectionModel(List<Projeto> projetos) {
        return projetos.stream()
                .map(projeto -> toModel(projeto))
                .collect(Collectors.toList());
    }

    private ProjetoModel toModel(Projeto projeto) {
        return modelMapper.map(projeto, ProjetoModel.class);
    }

    private Projeto toEntity(ProjetoModelInput projetoModelInput) {
        return modelMapper.map(projetoModelInput, Projeto.class);
    }

}
