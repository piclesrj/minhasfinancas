package com.prodrigues.minhasfinancas.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prodrigues.minhasfinancas.Exception.ErroAutenticacao;
import com.prodrigues.minhasfinancas.Exception.RegraDeNegocioException;
import com.prodrigues.minhasfinancas.api.dto.UsuarioDTO;
import com.prodrigues.minhasfinancas.model.entity.Usuario;
import com.prodrigues.minhasfinancas.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioResource {
	
private UsuarioService service;
	
	public UsuarioResource(UsuarioService service) {
		this.service = service;
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
		
		try {
			Usuario usuarioautenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioautenticado);
		}catch(ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@PostMapping
	public ResponseEntity salvar( @RequestBody UsuarioDTO dto) {
		Usuario usuario = Usuario.builder().nome(dto.getNome()).email(dto.getEmail()).senha(dto.getSenha()).build();
		
		try {
			
			Usuario usuariosalvo = service.salvarUsuario(usuario);
			return new ResponseEntity<Usuario>(usuariosalvo,HttpStatus.CREATED);
		}catch(RegraDeNegocioException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

}
