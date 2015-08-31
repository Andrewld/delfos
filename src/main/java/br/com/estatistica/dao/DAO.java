package br.com.estatistica.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 * Interface respons�vel por realizar um contrato com as classes que realizar�o opera��es
 * com o banco de dados.<br>
 * Essa interface dever� incluir as opera��es b�sicas que o banco pode fazer, tais como
 * salvar, cadastrar, excluir e etc.
 * 
 * @author lhleonardo
 *
 * @param <T>
 *            classe que ser� manipulada para realizar opera��es no banco de dados.
 */

public interface DAO<T> {

	/**
	 * Faz a inser��o ou atualiza��o na base de dados.
	 * 
	 * @param T
	 *            sendo a classe gen�rica que ser� recebida na declara��o da interface.
	 * @throws SQLException
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	void save(T modelo) throws SQLException, NullPointerException;

	/**
	 * Exclui o registro na base de dados
	 * 
	 * @param T
	 *            sendo a classe gen�rica que ser� recebida na declara��o da interface.
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	void remove(T modelo) throws SQLException, NullPointerException;

	/**
	 * M�todo que retorna todos os registros que estar�o presentes na base de dados
	 * 
	 * @return Lista com os registros.
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	List<T> getAll() throws SQLException;

	/**
	 * M�todo respons�vel por pesquisar os registros a partir de um nome.
	 * 
	 * @param nome
	 *            Filtro da pesquisa.
	 * @return Lista com filtro em nome.
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	List<T> getByNome(String nome) throws SQLException, NullPointerException;

	/**
	 * M�todo respons�vel por pesquisar o registro pelo c�digo.
	 * 
	 * @param id
	 *            filtro da pesquisa.
	 * @return T j� com o filtro, caso n�o encontre nada retorna <code>null</code>.
	 * @throws <code>RuntimeException</code> se algum problema ocorrer.
	 */
	T findById(Integer id) throws SQLException, NullPointerException;

	/**
	 * Inicializa o componente de persistencia.
	 */
	void init();

}
