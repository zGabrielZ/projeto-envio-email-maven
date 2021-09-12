package br.com.gabrielferreira.repositorio;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.gabrielferreira.conexao.ConexaoBD;
import br.com.gabrielferreira.entidade.Usuario;

public class UsuarioRepositorio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection conexao;
	
	public UsuarioRepositorio() {
		this.conexao = ConexaoBD.getConexao();
	}
	
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM USUARIO ORDER BY ID DESC";
		try {
			
			// Fazer a consulta 
			st = conexao.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("ID"));
				usuario.setNome(rs.getString("NOME"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setTelefone(rs.getString("TELEFONE"));
				usuario.setNascimento(rs.getDate("NASCIMENTO"));
				usuarios.add(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConexaoBD.fecharStatement(st);
			ConexaoBD.fecharResultSet(rs);
		}
		
		return usuarios;
	}

}
