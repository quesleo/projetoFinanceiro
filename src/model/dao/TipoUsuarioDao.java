package model.dao;

import java.util.List;

import model.entities.TipoUsuario;

public interface TipoUsuarioDao {

	void insert(TipoUsuario obj);
	void update(TipoUsuario obj);
	void deleteById(Integer idTipoUsuario);
	TipoUsuario findById(Integer TipoUsuario);
	List<TipoUsuario> findAll();
}
