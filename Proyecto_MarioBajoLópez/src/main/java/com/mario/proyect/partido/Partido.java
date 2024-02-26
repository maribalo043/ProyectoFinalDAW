package com.mario.proyect.partido;

import java.util.List;

import com.mario.proyect.categoria.Categoria;
import com.mario.proyect.juega.Juega;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "partidos")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String resultado;
    private String pista;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL)
    private List<Juega> juegas;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getPista() {
		return pista;
	}
	public void setPista(String pista) {
		this.pista = pista;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Juega> getJuegas() {
        return juegas;
    }
    public void setJuegas(List<Juega> juegas) {
        this.juegas = juegas;
    }
	@Override
	public String toString() {
		return "Partido [id=" + id + ", resultado=" + resultado + "]";
	}
}
