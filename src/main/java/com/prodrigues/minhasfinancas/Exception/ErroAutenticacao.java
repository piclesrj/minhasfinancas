package com.prodrigues.minhasfinancas.Exception;

public class ErroAutenticacao extends RuntimeException{

	public ErroAutenticacao(String mensagem) {
		super(mensagem);
	}
}
