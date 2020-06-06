package com.prodrigues.minhasfinancas.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.prodrigues.minhasfinancas.model.entity.Usuario;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {

		// Cenario
		Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com.br").build();
		repository.save(usuario);

		// Ação e execução
		boolean result = repository.getByEmail("usuario@email.com.br");

		// Verificação
		Assertions.assertThat(result).isTrue();
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		//Cenário
		repository.deleteAll();
		
		//Ação
		boolean result = repository.getByEmail("usuario@email.com.br");
		
		// Verificação
		Assertions.assertThat(result).isFalse();
	}
}
