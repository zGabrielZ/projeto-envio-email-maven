package br.com.gabrielferreira.teste.repositorio;

import java.util.List;

import org.junit.Test;

import br.com.gabrielferreira.entidade.Usuario;
import br.com.gabrielferreira.repositorio.UsuarioRepositorio;

public class TesteUsuarioRepositorio {

	public UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
	
	@Test
	public void listaUsuarios() {
		List<Usuario> usuarios = usuarioRepositorio.getUsuarios();
		for(Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}
	
}
