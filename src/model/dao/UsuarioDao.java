package model.dao;

import java.util.List;

import model.entities.TipoUsuario;
import model.entities.Usuario;

public interface UsuarioDao {

	void insert(Usuario obj);
	void update(Usuario obj);
	void deleteById(Integer idUsuario);
	Usuario findById(Integer idUsuario);
	List<Usuario> findAll();
	List<Usuario> findByTipo(TipoUsuario tipoUsuario);
}
