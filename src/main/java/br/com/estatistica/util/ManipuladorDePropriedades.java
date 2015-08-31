package br.com.estatistica.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe respons�vel por manipular os arquivos de propriedades
 * 
 * @author Leonardo Braz
 * @since 5.0
 */
public class ManipuladorDePropriedades {

	/**
	 * Atributo respons�vel por armazenar o caminho que se encontra o arquivo de
	 * manipula��o.
	 */
	private String arquivo;

	/**
	 * Construtor que recebe o caminho de onde se encontra o arquivo de propriedades
	 * 
	 * @param arquivo
	 *            localiza��o do arquivo
	 */
	public ManipuladorDePropriedades(String arquivo) {
		this.arquivo = arquivo;
	}

	/**
	 * M�todo respons�vel por preparar o arquivo para sua manipula��o.
	 * 
	 * @return Properties inst�ncia de Properties pronta para manipula��o.
	 * @throws IOException
	 *             caso o caminho do arquivo informado no construtor n�o for encontrado
	 *             ou v�lido
	 * @author Leonardo Braz
	 */

	public Properties getProp() throws IOException {
		Properties properties = new Properties();
		// indica um caminho da localiza��o do arquivo
		FileInputStream file = new FileInputStream(this.arquivo);
		// instancia esse arquivo
		properties.load(file);
		return properties;
	}

	public String getProperty(String key) throws IOException {
		return getProp().getProperty(key);
	}

}
