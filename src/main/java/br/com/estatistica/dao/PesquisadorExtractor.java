package br.com.estatistica.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.estatistica.modelo.cadastro.Cpf;
import br.com.estatistica.modelo.cadastro.Pesquisador;

/**
 * Classe respons�vel por fazer a extra��o dos dados presentes no banco e tranform�-los
 * em objetos
 * 
 * @author lhleonardo - Leonardo Braz
 * @since 1.4
 */
public class PesquisadorExtractor {

	/**
	 * M�todo que extrai as informa��es de um pesquisador no Banco de Dados e transforma
	 * em um objeto manipul�vel
	 * 
	 * @param rs
	 *            resultado de pesquisa de query
	 * @return Pesquisador pr� moldado com as informa��es do banco de dados.
	 * @throws SQLException
	 *             para erro de consulta
	 */
	public Pesquisador extract(ResultSet rs) throws SQLException {
		int id = rs.getInt("idPesquisador");
		String nome = rs.getString("nome");
		Cpf cpf = new Cpf(rs.getString("cpf"));
		String rg = rs.getString("rg");
		Date dataNascimento = rs.getDate("dataNascimento");
		String descricao = rs.getString("descricao");

		return new Pesquisador(id, nome, cpf, rg, dataNascimento, descricao);
	}

	/**
	 * M�todo que realiza as mesmas opera��es do <code>extract</code>, entretanto �
	 * utilizado para mais de uma extra��o de informa��es ao banco de dados
	 * 
	 * @param rs
	 * @return Lista de pesquisadores
	 * @throws SQLException
	 */
	public List<Pesquisador> extractAll(ResultSet rs) throws SQLException {
		List<Pesquisador> resultado = new ArrayList<Pesquisador>();

		while (rs.next()) {
			resultado.add(this.extract(rs));
		}

		return resultado;
	}
}
