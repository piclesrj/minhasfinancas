package com.prodrigues.minhasfinancas.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prodrigues.minhasfinancas.Exception.RegraDeNegocioException;
import com.prodrigues.minhasfinancas.model.entity.Lancamento;
import com.prodrigues.minhasfinancas.model.enums.StatusLancamento;
import com.prodrigues.minhasfinancas.model.repository.LancamentoRespository;
import com.prodrigues.minhasfinancas.service.LancamentoService;


@Service
public class LancamentoServiceImpl implements LancamentoService{

	private LancamentoRespository repository;
	
	public LancamentoServiceImpl(LancamentoRespository repository) {
		this.repository = repository;
	}
	
	@Override
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		validar(lancamento);
		lancamento.setStatus(StatusLancamento.PENDENTE);
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		validar(lancamento);
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		repository.delete(lancamento);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
		Example example = Example.of(lancamentoFiltro,ExampleMatcher.matching()
				.withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return repository.findAll(example);
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
		lancamento.setStatus(status);
		atualizar(lancamento);
		
	}

	@Override
	public void validar(Lancamento lancamento) {
		if(lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")){
			throw new RegraDeNegocioException("Informe uma descricão valida!!");
		}
		if(lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
			throw new RegraDeNegocioException("Informe um Mes valido!!");
		}
		if(lancamento.getAno() == null || lancamento.getAno().toString().length() != 4) {
			throw new RegraDeNegocioException("Informe um Ano valido!!");
		}
		if(lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
			throw new RegraDeNegocioException("Informe um Usuario valido!!");
		}
		if(lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1 ) {
			throw new RegraDeNegocioException("Informe um Valor valido!!");
		}
		if(lancamento.getTipo() == null) {
			throw new RegraDeNegocioException("Informe o Tipo de lancamento!!");
		}
	}

}
