package br.com.estatistica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.estatistica.modelos.Bairro;

public class BairroDAO extends GenericDAO<Bairro> {

	private static final String SQL_INSERT = "INSERT INTO Bairro(nome, descricao) VALUES (?,?);";
	private static final String SQL_UPDATE = "UPDATE Bairro SET nome = ?, descricao = ? WHERE id_bairro = ?;";
	private static final String SQL_DELETE = "DELETE FROM Bairro WHERE id_bairro = ?";
	private static final String SQL_SELECT = "SELECT * FROM Bairro";

	public BairroDAO(Connection connection) {
		super(connection);
	}

	@Override
	protected Integer insert(Bairro model) throws SQLException {
		try (PreparedStatement pst = super.getConnection().prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, model.getNome());
			pst.setString(2, model.getDescricao());
			pst.executeUpdate();
			return super.getGeneratedKeys(pst.getGeneratedKeys(), "id_bairro");
		}

	}

	@Override
	protected Integer update(Bairro model) throws SQLException {
		try (PreparedStatement pst = super.getConnection().prepareStatement(SQL_UPDATE, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pst.setInt(1, model.getId());
			pst.setString(1, model.getNome());
			pst.setString(2, model.getDescricao());
			pst.executeUpdate();
			return super.getGeneratedKeys(pst.getGeneratedKeys(), "id_bairro");
		}

	}

	@Override
	public boolean delete(Bairro model) throws SQLException {
		try (PreparedStatement pst = super.getConnection().prepareStatement(SQL_DELETE)) {
			pst.setInt(1, model.getId());
			pst.executeUpdate();
			return this.get(model.getId()) == null;

		}
	}

	@Override
	public List<Bairro> getAll() throws SQLException {
		List<Bairro> bairros = new ArrayList<Bairro>();

		try (PreparedStatement pst = super.getConnection().prepareStatement(SQL_SELECT)) {
			ResultSet resultSet = pst.executeQuery();

			bairros.addAll();
		}

		return bairros;
	}

	@Override
	public Bairro get(Bairro model) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bairro get(Integer idModel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bairro get(String value) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExist(Bairro model) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExist(Integer idModel) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}