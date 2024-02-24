package br.com.ibm.consulting.bootcamp.demospring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String autor;

	private String titulo;

	@JsonProperty("ano_publicacao")
	private String anoPublicacao;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "livro", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Reserva> reservas = new ArrayList<>();

	private long exemplar = 0;
	
	public Livro() {
		
	}
	
	public Livro(long id, String autor, String titulo, String anoPublicacao) {
		this.id = id;
		this.autor = autor;
		this.titulo = titulo;
		this.anoPublicacao = anoPublicacao;
	}

	public Livro(long id, long exemplar) {
		this.id = id;
		this.exemplar = exemplar;
	}

}
