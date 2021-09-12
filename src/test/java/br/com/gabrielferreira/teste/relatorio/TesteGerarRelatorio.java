package br.com.gabrielferreira.teste.relatorio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import br.com.gabrielferreira.servico.UsuarioServico;

public class TesteGerarRelatorio {

	public UsuarioServico usuarioServico = new UsuarioServico();
	
	@Test
	public void testeGerarRelatorio() throws IOException {
		byte[] bytesArquivo = usuarioServico.gerarRelatorioUsuario();
		File file = new File("relatorio_usuario.pdf");
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(bytesArquivo);
		fileOutputStream.flush();
		fileOutputStream.close();
		System.out.println("Relatório gerado com sucesso !!");
	}
	
}
