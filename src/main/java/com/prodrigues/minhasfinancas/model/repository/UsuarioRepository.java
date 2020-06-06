package com.prodrigues.minhasfinancas.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodrigues.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	//Utilização de query method.
	String getByEmail(String email);
	
	Optional<Usuario> findByEmail(String email);

}
