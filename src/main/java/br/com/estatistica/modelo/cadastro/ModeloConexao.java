package br.com.estatistica.modelo.cadastro;

import java.io.IOException;

import br.com.estatistica.util.ManipuladorDePropriedades;
import br.com.estatistica.util.Mensagem;

/**
 * Classe modelo para as configura��es de conex�o com o banco de dados, contendo <b>Host,
 * Caminho para o banco de dados, Driver, Usu�rio e Senha</b> para conex�o.
 * 
 * @author Leonardo Braz
 * @since 1.6
 * @version 0.1
 * 
 */
public class ModeloConexao {

	public ManipuladorDePropriedades manipulador;

	public ModeloConexao() {
		this.manipulador = new ManipuladorDePropriedades("src/main/resources/delfos.properties");

		try {
			this.setCaminhoDatabase(manipulador.getProp().getProperty("database.nomeDatabase"));
			this.setDriver(manipulador.getProp().getProperty("database.driver"));
			this.setHost(manipulador.getProp().getProperty("database.host"));
			this.setUsuario(manipulador.getProp().getProperty("database.usuario"));
			this.setSenha(manipulador.getProp().getProperty("database.senha"));
		} catch (IOException e) {
			Mensagem.entrada(e.getMessage());
		}
	}

	/**
	 * Atributo respons�vel por indicar a localiza��o do servidor onde se encontram o
	 * banco de dados, podendo ser em uma rede local ou externa.
	 * 
	 */
	private String host;

	/**
	 * Nome do schema do banco de dados.
	 */
	private String nomeDatabase;

	/**
	 * Caminho do driver de gerenciamento de determinado banco. <br>
	 * Geralmente � encontrado em uma biblioteca .jar
	 */
	private String driver;

	/**
	 * Usu�rio para a conex�o do banco de dados.
	 */
	private String usuario;

	/**
	 * Senha para autentica��o do usu�rio no banco de dados.
	 */
	private String senha;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getNomeDatabase() {
		return nomeDatabase;
	}

	public void setCaminhoDatabase(String caminhoDatabase) {
		this.nomeDatabase = caminhoDatabase;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * M�todo respons�vel por retornar a URL para conex�o j� formatada, contendo o seu
	 * tipo de conector(JDBC), o host onde se encontra o servidor e o endere�o para a
	 * conex�o.
	 * 
	 * @author Leonardo Braz
	 * @return String
	 * @since 1.4
	 */
	public String getURL() {
		return "jdbc:" + getJdbc() + "://" + this.getHost().trim() + '/' + this.getNomeDatabase().trim();
	}

	/**
	 * M�todo respons�vel por descobrir o tipo de banco a partir da classe de conex�o
	 * utilizada pelo driver, sendo MySQL, Firebird, Oracle, etc.
	 * 
	 * @since 1.4
	 * @author Leonardo Braz
	 * @return String
	 */
	public String getJdbc() {
		String driver = this.getDriver();
		driver = driver.replace(".", ";");
		String[] valores = driver.split(";");

		return getJdbcPelaClasseDoDriver(valores);

	}

	/**
	 * M�todo respons�vel por retornar o Jdbc pela classe utilizada pelo Driver, onde �
	 * utilizado pelo m�todo getJdbc.
	 * 
	 * @param valores
	 *            que ser�o informados pelo m�todo getJdbc
	 * @return String
	 * @author Leonardo Braz
	 * 
	 */
	private String getJdbcPelaClasseDoDriver(String[] valores) {
		String resultado = null;
		for (String string : valores) {
			string = string.trim();
			if ((string.equals("mysql")) || (string.equals("firebirdsql"))) {
				resultado = string;
			}
		}
		return resultado;
	}

}
