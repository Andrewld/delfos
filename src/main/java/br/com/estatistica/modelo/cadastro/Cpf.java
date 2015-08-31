package br.com.estatistica.modelo.cadastro;

public class Cpf extends CadastroNacionalDePessoa {
	/**
	 * 
	 * M�todo de inicializa��o da classe CPF
	 * 
	 * @param valor
	 *            - Numera��o do CPF sem m�scara.
	 */
	public Cpf(String valor) {
		super(valor);
	}

	/**
	 * 
	 * M�todo de inicializa��o da classe CPF sem receber parametros para inicializa��o.
	 */

	public Cpf() {
	}

	/**
	 * Constante que representa o peso relacionado ao CPF. <br>
	 * Para mais informa��es, acesse o m�todo {@link #verifica()}
	 */
	private static final int[] peso = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	/**
	 * M�todo respons�vel por fazer a verifica��o dos d�gitos validadores de um CPF a
	 * partir da fun��o {@link #calcularDigito(String, int[])} encontrado na classe
	 * {@code CadastroNacionalDePessoa}.
	 * 
	 * @return true para CPF v�lido a partir do validador e false para CPF inv�lido.
	 */
	@Override
	protected boolean verifica() {
		if ((valor == null) || (valor.length() != 11)) return false;

		Integer digito1 = calcularDigito(valor.substring(0, 9), peso);
		Integer digito2 = calcularDigito(valor.substring(0, 9) + digito1, peso);
		return valor.equals(valor.substring(0, 9) + digito1.toString() + digito2.toString());
	}

	/**
	 * M�todo respons�vel por concatenar o CPF a partir de sua m�scara padr�o.
	 * 
	 * @return CPF j� mascarado.
	 */
	@Override
	public String imprime() {
		// TODO Auto-generated method stub
		return (getValor().substring(0, 3) + "." + getValor().substring(3, 6) + "."
				+ getValor().substring(6, 9) + "-" + getValor().substring(9, 11));
	}

}
