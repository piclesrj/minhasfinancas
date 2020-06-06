package com.prodrigues.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodrigues.minhasfinancas.model.entity.Lancamento;

public interface LancamentoRespository extends JpaRepository<Lancamento, Long>{

}
