package br.com.ibm.consulting.bootcamp.demospring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

	@OneToMany(mappedBy = "livro", fetch = FetchType.LAZY)
	private List<Reserva> reservas = new ArrayList<>();
	
	public Livro() {
		
	}
	
	public Livro(long id, String autor, String titulo, String anoPublicacao) {
		this.id = id;
		this.autor = autor;
		this.titulo = titulo;
		this.anoPublicacao = anoPublicacao;
	}

}
