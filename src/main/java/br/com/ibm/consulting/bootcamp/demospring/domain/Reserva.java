package br.com.ibm.consulting.bootcamp.demospring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Reserva {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public long id;

  public String usuario;

  @JsonProperty("data_inicio")
  public String dataInicio;

  @JsonProperty("data_fim")
  public String dataFim;

  @ManyToOne
  @JoinColumn(name = "livroId")
  public Livro livro;

  public Reserva() {

  }

  public Reserva(Livro livro, String usuario, String dataInicio, String dataFim) {
    this.livro = livro;
    this.usuario = usuario;
    this.dataFim = dataFim;
    this.dataInicio = dataInicio;
  }
}
