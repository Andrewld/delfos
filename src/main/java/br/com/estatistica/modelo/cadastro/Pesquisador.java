package br.com.estatistica.modelo.cadastro;

import java.util.Date;

/**
 * Classe respons�vel por ser um modelador de informa��es dos <code>Pesquisadores</code>
 * que ser�o cadastrados no sistema.
 * 
 * @see Pessoa
 * @author Leonardo Braz
 * @since 1.4
 */

public class Pesquisador extends Pessoa {

	public Pesquisador(String nome, Documento tipoDocumento, String rg, Date dataNascimento, String descricao) {

		super(nome, tipoDocumento, rg, dataNascimento, descricao);
	}

	public Pesquisador(Integer id, String nome, Cpf cpf, String rg, Date dataNascimento, String descricao) {

		super(id, nome, cpf, rg, dataNascimento, descricao);
	}

}
