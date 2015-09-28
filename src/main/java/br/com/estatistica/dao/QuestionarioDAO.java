package br.com.estatistica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.estatistica.modelos.Questionario;

public class QuestionarioDAO extends GenericDAO<Questionario> {
	
	private static final String SQL_SELECT = "SELECT * FROM Questionario";
	private static final String SQL_SELECT_WHERE = SQL_SELECT + " WHERE id_questionario = ? AND data = ?";
	private static final String SQL_SELECT_BY_ID = SQL_SELECT + " WHERE id_questionario = ?";
	private static final String SQL_SELECT_BY_DATA = SQL_SELECT + " WHERE data = ?";
	private static final String SQL_SELECT_BY_NOME = SQL_SELECT + " WHERE nome = ?";
	private static final String SQL_INSERT = "INSERT INTO Questionario(id_questionario,nome,descricao) VALUES (?,?,?)";
	private static final String SQL_DELETE = "DELETE FROM Pesquisa WHERE id_questionario = ?";
	
	public QuestionarioDAO(Connection connection) {
		super(connection);
	}
	
	
	
	@Override
	protected Integer insert(Questionario model) throws SQLException {
		try (PreparedStatement pst = super.getConnection().prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pst.setInt(1, model.getId());
			pst.setString(2, model.getNome());
			pst.setString(3, model.getDescricao());
			pst.executeUpdate();
			return super.getGeneratedKeys(pst.getGeneratedKeys());
		}
	}

	@Override
	protected Integer update(Questionario model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Questionario model) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Questionario> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Questionario get(Questionario model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Questionario get(Integer idModel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Questionario> get(String value) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExist(Questionario model) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExist(Integer idModel) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	

}
