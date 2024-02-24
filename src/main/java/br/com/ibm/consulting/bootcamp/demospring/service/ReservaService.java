package br.com.ibm.consulting.bootcamp.demospring.service;

import br.com.ibm.consulting.bootcamp.demospring.domain.Reserva;
import br.com.ibm.consulting.bootcamp.demospring.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservaService {
  @Autowired
  ReservaRepository repository;

  public List<Reserva> listarReservasPorLivro(long livroId) {
    return this.repository.findByLivro_Id(livroId);
  }

  public Reserva criarReserva(Reserva r) {
    return this.repository.saveAndFlush(r);
  }
}
