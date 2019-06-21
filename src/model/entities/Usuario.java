package model.entities;

import java.io.Serializable;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idUsuario;
	private String nomeUsuario;
	private TipoUsuario tipoUsuario;
	
	public Usuario() {
	}
	
	

	public Usuario(Integer idUsuario, String nomeUsuario, TipoUsuario tipoUsuario) {

		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.tipoUsuario = tipoUsuario;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNameUsuario() {
		return nomeUsuario;
	}

	public void setNameUsuario(String nameUsuario) {
		this.nomeUsuario = nameUsuario;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nomeUsuario=" + nomeUsuario + ", tipoUsuario=" + tipoUsuario
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
		result = prime * result + ((tipoUsuario == null) ? 0 : tipoUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (nomeUsuario == null) {
			if (other.nomeUsuario != null)
				return false;
		} else if (!nomeUsuario.equals(other.nomeUsuario))
			return false;
		if (tipoUsuario == null) {
			if (other.tipoUsuario != null)
				return false;
		} else if (!tipoUsuario.equals(other.tipoUsuario))
			return false;
		return true;
	}

}
