package br.com.estatistica.modelo.cadastro;

import java.util.Date;

/**
 * Classe respons�vel por ser um modelador de informa��es dos Especialistas que ser�o
 * cadastrados no sistema.
 * 
 * @see Pessoa
 * @version 1.0
 * @author Leonardo Braz - lhleonardo@hotmail.com
 * @since 3.0
 */

public class Especialista extends Pessoa {

	public Especialista(String nome, Documento tipoDocumento, String rg, Date dataNascimento, String descricao) {
		super(nome, tipoDocumento, rg, dataNascimento, descricao);
	}

}
