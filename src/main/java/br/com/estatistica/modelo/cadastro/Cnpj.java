package br.com.estatistica.modelo.cadastro;

public class Cnpj extends CadastroNacionalDePessoa {

	/**
	 * 
	 * M�todo de inicializa��o da classe Cnpj sem receber parametros para inicializa��o.
	 */

	public Cnpj() {
		super();
	}

	/**
	 * 
	 * M�todo de inicializa��o da classe Cnpj
	 * 
	 * @param valor
	 *            - Numera��o do CNPJ sem mascara.
	 */
	public Cnpj(String valor) {
		super(valor);
	}

	/**
	 * Constante que representa o peso relacionado ao CNPJ. <br>
	 * Para mais informa��es, acesse o m�todo {@link #verifica()}
	 */
	private static final int[] peso = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	/**
	 * M�todo respons�vel por fazer a verifica��o dos d�gitos validadores de um CNPJ a
	 * partir da fun��o {@link #calcularDigito(String, int[])} encontrado na classe
	 * {@code CadastroNacionalDePessoa}.
	 * 
	 * @return true para CNPJ v�lido a partir do validador e false para CNPJ inv�lido.
	 */
	@Override
	protected boolean verifica() {
		if ((super.valor == null) || (super.valor.length() != 14)) return false;

		Integer digito1 = calcularDigito(super.valor.substring(0, 12), peso);
		Integer digito2 = calcularDigito(super.valor.substring(0, 12) + digito1, peso);
		return super.valor.equals(super.valor.substring(0, 12) + digito1.toString() + digito2.toString());
	}

	/**
	 * M�todo respons�vel por concatenar o CNPJ a partir de sua m�scara padr�o.
	 * 
	 * @return CNPJ j� mascarado.
	 */
	@Override
	public String imprime() {
		return (getValor().substring(0, 2) + "." + getValor().substring(2, 5) + "."
				+ getValor().substring(5, 8) + "." + getValor().substring(8, 12) + "-" + getValor()
				.substring(12, 14));

	}
}
