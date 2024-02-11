package com.mario.proyect.juega;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class JuegaKey implements Serializable {

    @Column(name = "equipo_id")
    private Long equipoId;

    @Column(name = "partido_id")
    private Long partidoId;

	public Long getEquipoId() {
		return equipoId;
	}

	public void setEquipoId(Long equipoId) {
		this.equipoId = equipoId;
	}

	public Long getPartidoId() {
		return partidoId;
	}

	public void setPartidoId(Long partidoId) {
		this.partidoId = partidoId;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(equipoId, partidoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JuegaKey other = (JuegaKey) obj;
		return Objects.equals(equipoId, other.equipoId) && Objects.equals(partidoId, other.partidoId);
	}

	@Override
	public String toString() {
		return "JuegaKey [equipoId=" + equipoId + ", partidoId=" + partidoId + "]";
	}
}

