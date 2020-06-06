package com.prodrigues.minhasfinancas.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prodrigues.minhasfinancas.Exception.ErroAutenticacao;
import com.prodrigues.minhasfinancas.Exception.RegraDeNegocioException;
import com.prodrigues.minhasfinancas.model.entity.Usuario;
import com.prodrigues.minhasfinancas.model.repository.UsuarioRepository;
import com.prodrigues.minhasfinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository repository;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository respository, UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário Não Encontrado para o email informado!");
		}
		
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha Inválida!");
		}
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		//String existe = repository.getByEmail(email);
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(usuario != null) {
			//existe = false;
			throw new RegraDeNegocioException("Já existe um usuario cadastrado com este email");
		}
		
	}

}
