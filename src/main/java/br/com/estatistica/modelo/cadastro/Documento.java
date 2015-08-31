package br.com.estatistica.modelo.cadastro;

/**
 * Interface <b>Documento</b> respons�vel por realizar um contrato entre os documentos
 * que uma {@link Pessoa} pode ter.
 * 
 * @author lhleonardo
 *
 */
public interface Documento {
	/**
	 * M�todo respons�vel por informar o conte�do do documento.
	 * 
	 * @return valor do Documento
	 */
	String getValor();

	/**
	 * M�todo respons�vel por validar os D�gitos Verificadores de um documento.
	 * 
	 * @return ...
	 */
	boolean isValido();

	/**
	 * M�todo respons�vel por mascarar um documento.
	 * 
	 * @return Documento com sua m�scara espec�fica.
	 */
	String imprime();

}
