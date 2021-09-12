package br.com.gabrielferreira.teste.conexao;

import org.junit.Test;

import br.com.gabrielferreira.conexao.ConexaoBD;

public class TesteConexaoBD {

	
	@Test
	public void testarConexao() {
		ConexaoBD.getConexao();
	}
}
