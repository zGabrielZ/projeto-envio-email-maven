package br.com.gabrielferreira.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoBD {

	private static Connection conexao = null;
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String bancoUrl = "jdbc:mysql://localhost/usuarios_emails";
	private static String senha = "gabriel";
	private static String usuario = "root";
	
	
	static {
		conectar();
	}
	
	public ConexaoBD() {
		conectar();
	}
	
	private static void conectar() {
		try {
			if (conexao == null) {
				Class.forName(driver);
				conexao = DriverManager.getConnection(bancoUrl, usuario, senha);

				conexao.setAutoCommit(false);

				System.out.println("Conectado com sucesso !!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void fecharConexao() {
		try {
			if(conexao != null) {
				conexao.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void fecharStatement(PreparedStatement st) {
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void fecharResultSet(ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConexao() {
		return conexao;
	}
}
