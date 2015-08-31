package br.com.estatistica.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.estatistica.modelo.cadastro.ModeloConexao;
import br.com.estatistica.util.Mensagem;

/**
 * Classe respons�vel pelas opera��es de conex�o do banco de dados, como conectar e
 * desconectar.
 * 
 * @author Leonardo Braz
 * @since 1.0
 * @version 1.0
 */
public class ConnectionFactory {

	/**
	 * Classe manipuladora do modelo de conex�es, que ser� respons�vel por trazer as
	 * informa��es necess�rias para abrir uma conex�o com o banco.
	 */
	private ModeloConexao modelo;

	/**
	 * Interface respons�vel por gerenciar a conex�o.
	 * 
	 * @see Connection
	 */
	private Connection con = null;

	/**
	 * Construtor para utiliza��o da classe, recebendo objeto do tipo ModeloConexao
	 * 
	 * @param modelo
	 *            {@link ModeloConexao} com as informa��es pr�-definidas
	 * @author Leonardo Braz
	 * @see ModeloConexao
	 */
	public ConnectionFactory(ModeloConexao modelo) {
		this.modelo = modelo;
	}

	public ConnectionFactory() {
		this.modelo = new ModeloConexao();
	}

	/**
	 * Respons�vel por realizar a conex�o bruta com o banco de dados, retornando um
	 * Connection que j� tenha a conex�o pronta para uso
	 * 
	 * @return Connection Conex�o pronta para ser manipulada.
	 * @author Leonardo Braz
	 */
	public Connection getConnection() {
		try {
			// salva na mem�ria a classe de conex�o do conector java
			Class.forName(modelo.getDriver());
			// realiza a conex�o recebendo a URL, usu�rio e senha
			con = DriverManager.getConnection(modelo.getURL(), modelo.getUsuario(), modelo.getSenha());

			System.out.println("Conectado.");

		} catch (SQLException e) {
			// erro SQL
			Mensagem.informa("N�o foi poss�vel conectar-se ao banco de dados pelo devido motivo: \n"
			        + e.getMessage());
		} catch (ClassNotFoundException e) {
			// n�o encontrou a classe do conector
			Mensagem.informa("N�o foi poss�vel conectar-se ao banco de dados pois a classe \n" + modelo.getDriver()
			        + " \nn�o foi encontrada.");
		}
		return con;

	}

	/**
	 * Respons�vel por desconectar o banco de dados, recebendo uma conex�o
	 * <b>Connection</b> que n�o esteja nula.
	 * 
	 * @return true para desconectado e false para falha.
	 * @param conexao
	 *            Connection que ser� fechada
	 * @author Leonardo Braz
	 */
	public boolean desconectar(Connection conexao) {
		boolean resultado = false;
		try {
			if (conexao != null) {
				conexao.close();
				resultado = true;
			}
		} catch (SQLException ex) {
			Mensagem.informa("N�o foi possivel desconectar-se ao banco de dados pelo devido motivo: \n"
			        + ex.getMessage());
			resultado = false;
		}
		return resultado;
	}

}
