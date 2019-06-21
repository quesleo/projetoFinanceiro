package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.TipoUsuarioDao;
import model.entities.TipoUsuario;

public class TipoUsuarioDaoJDBC implements TipoUsuarioDao {

	private Connection conn;
	
	public TipoUsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public TipoUsuario findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM TipoUsuario WHERE idTipoUsuario = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				TipoUsuario obj = new TipoUsuario();
				obj.setIdTipoUsuario(rs.getInt("idTipoUsuario"));
				obj.setNameTipoUsuario(rs.getString("nomeTipoUsuario"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<TipoUsuario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM TipoUsuario ORDER BY nomeTipoUsuario");
			rs = st.executeQuery();

			List<TipoUsuario> list = new ArrayList<>();

			while (rs.next()) {
				TipoUsuario obj = new TipoUsuario();
				obj.setIdTipoUsuario(rs.getInt("idTipoUsuario"));
				obj.setNameTipoUsuario(rs.getString("nomeTipoUsuario"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insert(TipoUsuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO TipoUsuario " +
				"(nomeTipoUsuario) " +
				"VALUES " +
				"(?)", 
				Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNameTipoUsuario());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdTipoUsuario(id);
				}
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(TipoUsuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"UPDATE TipoUsuario " +
				"SET nomeTipoUsuario = ? " +
				"WHERE idTipoUsuario = ?");

			st.setString(1, obj.getNameTipoUsuario());
			st.setInt(2, obj.getIdTipoUsuario());

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM TipoUsuario WHERE idTipoUsuario = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}
}
