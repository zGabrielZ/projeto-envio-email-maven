package br.com.gabrielferreira.servico;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.gabrielferreira.entidade.Usuario;
import br.com.gabrielferreira.repositorio.UsuarioRepositorio;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class UsuarioServico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
	
	private List<Usuario> getUsuarios(){
		return usuarioRepositorio.getUsuarios();
	}
	
	public byte[] gerarRelatorioUsuario() {
		List<Usuario> usuarios = getUsuarios();
		byte[] bytesArquivo = null;
		try {
			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			//String caminhoTeste = "C:\\Users\\Acer\\Desktop\\Curso Java\\Projetos\\ProjetoEnvioEmail\\src\\main\\webapp\\resources\\relatorio\\usuario\\Usuario.jrxml";
			String caminho = facesContext.getExternalContext().getRealPath("/resources/relatorio/usuario/Usuario.jrxml");
			JasperReport compilarRelatorio = JasperCompileManager.compileReport(caminho);
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(usuarios);
			Map<String, Object> paramatros = new LinkedHashMap<String, Object>();
			JasperPrint jasperPrint = JasperFillManager.fillReport(compilarRelatorio,paramatros,dataSource);
			bytesArquivo = JasperExportManager.exportReportToPdf(jasperPrint);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(e.getMessage());
		}

		return bytesArquivo;
	}

}
