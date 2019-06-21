package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.UsuarioDao;
import model.entities.TipoUsuario;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao {

	private Connection conn;
	
	public UsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"insert into Usuario"
					+ "(idUsuario, nomeUsuario, idTipoUsuario) "
					+ "VALUES "
					+ "(?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getIdUsuario());
			st.setString(2, obj.getNameUsuario());
			st.setInt(3, obj.getTipoUsuario().getIdTipoUsuario());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int idUsuario = rs.getInt(1);
					obj.setIdUsuario(idUsuario);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro não esperado! Nenhuma linha afetada!");
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
	public void update(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Usuario "
					+ "SET nomeUsuario = ?, idTipoUsuario = ? "
					+ "WHERE idUsuario = ?");
			
			st.setString(1, obj.getNameUsuario());
			st.setInt(2, obj.getTipoUsuario().getIdTipoUsuario());
			st.setInt(3, obj.getIdUsuario());

			
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
	public void deleteById(Integer idUsuario) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM Usuario WHERE idUsuario = ?");
			
			st.setInt(1, idUsuario);
			
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
	public Usuario findById(Integer idUsuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT nomeUsuario, nomeTipoUsuario as TIPO "
					+ "FROM Usuario INNER JOIN TipoUsuario "
					+ "ON Usuario.idTipoUsuario = TipoUsuario.idTipoUsuario "
					+ "WHERE Usuario.idUsuario = ?");
			
			st.setInt(1, idUsuario);
			rs = st.executeQuery();
			
			if (rs.next()) {
				TipoUsuario tipo = instantiateTipoUsuario(rs);
				Usuario obj = instantiateUsuario(rs, tipo);
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

	private Usuario instantiateUsuario(ResultSet rs, TipoUsuario tipo) throws SQLException {
		Usuario obj = new Usuario();
		obj.setIdUsuario(rs.getInt("idUsuario"));
		obj.setNameUsuario(rs.getString("nomeUsuario"));
		obj.setTipoUsuario(tipo);
		return obj;
	}

	private TipoUsuario instantiateTipoUsuario(ResultSet rs) throws SQLException {
		TipoUsuario tipo = new TipoUsuario();
		tipo.setIdTipoUsuario(rs.getInt("idTipoUsuario"));
		tipo.setNameTipoUsuario(rs.getString("nomeTipoUsuario"));
		return tipo;
	}

	@Override
	public List<Usuario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Usuario.nomeUsuario, TipoUsuario.nomeTipoUsuario as TIPO "
					+ "FROM Usuario INNER JOIN TipoUsuario "
					+ "ON Usuario.idTipoUsuario = TipoUsuario.idTipoUsuario ");
			
			rs = st.executeQuery();
			
			List<Usuario> list = new ArrayList<>();
			Map<Integer, TipoUsuario> map = new HashMap<>();
			
			while (rs.next()) {
				
				TipoUsuario tipo = map.get(rs.getInt("idTipoUsuario"));
				
				if (tipo == null) {
					tipo = instantiateTipoUsuario(rs);
					map.put(rs.getInt("idTipoUsuario"), tipo);
				}
				
				Usuario obj = instantiateUsuario(rs, tipo);
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
	public List<Usuario> findByTipo(TipoUsuario tipoUsuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Usuario.nomeUsuario, TipoUsuario.nomeTipoUsuario as TIPO "
					+ "FROM Usuario INNER JOIN TipoUsuario "
					+ "ON Usuario.idTipoUsuario = TipoUsuario.idTipoUsuario "
					+ "WHERE idTipoUsuario = ?");
			
			st.setInt(1, tipoUsuario.getIdTipoUsuario());
			
			rs = st.executeQuery();
			
			List<Usuario> list = new ArrayList<>();
			Map<Integer, TipoUsuario> map = new HashMap<>();
			
			while (rs.next()) {
				
				TipoUsuario tipo = map.get(rs.getInt("idTipoUsuario"));
				
				if (tipo == null) {
					tipo = instantiateTipoUsuario(rs);
					map.put(rs.getInt("idTipoUsuario"), tipo);
				}
				
				Usuario obj = instantiateUsuario(rs, tipo);
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
}
