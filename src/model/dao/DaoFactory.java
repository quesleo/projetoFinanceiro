package model.dao;

import db.DB;
import model.dao.impl.TipoUsuarioDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;

public class DaoFactory {

	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}
	
	public static TipoUsuarioDao createTipoUsuarioDao() {
		return new TipoUsuarioDaoJDBC(DB.getConnection());
	}
}
