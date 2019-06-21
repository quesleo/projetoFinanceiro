package model.entities;

import java.io.Serializable;

public class TipoUsuario implements Serializable {

	private static final long serialVersionUID = 1L;


	private Integer idTipoUsuario;
	private String nameTipoUsuario;
	
	public TipoUsuario() {
	}

	public TipoUsuario(Integer idTipoUsuario, String nameTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
		this.nameTipoUsuario = nameTipoUsuario;
	}

	@Override
	public String toString() {
		return "TipoUsuario [idTipoUsuario=" + idTipoUsuario + ", nameTipoUsuario=" + nameTipoUsuario + "]";
	}
	public Integer getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(Integer idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getNameTipoUsuario() {
		return nameTipoUsuario;
	}

	public void setNameTipoUsuario(String nameTipoUsuario) {
		this.nameTipoUsuario = nameTipoUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTipoUsuario == null) ? 0 : idTipoUsuario.hashCode());
		result = prime * result + ((nameTipoUsuario == null) ? 0 : nameTipoUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoUsuario other = (TipoUsuario) obj;
		if (idTipoUsuario == null) {
			if (other.idTipoUsuario != null)
				return false;
		} else if (!idTipoUsuario.equals(other.idTipoUsuario))
			return false;
		if (nameTipoUsuario == null) {
			if (other.nameTipoUsuario != null)
				return false;
		} else if (!nameTipoUsuario.equals(other.nameTipoUsuario))
			return false;
		return true;
	}

	

}
