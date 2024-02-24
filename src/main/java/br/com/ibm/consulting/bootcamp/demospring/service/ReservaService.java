package br.com.ibm.consulting.bootcamp.demospring.service;

import br.com.ibm.consulting.bootcamp.demospring.domain.Reserva;
import br.com.ibm.consulting.bootcamp.demospring.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservaService {
  @Autowired
  ReservaRepository repository;

  public Page<Reserva> listarReservasPorLivro(long livroId, Integer pagina, Integer porPagina) {
    if (pagina == null) pagina = 0;
    if (porPagina == null) porPagina = 15;
    return this.repository.findByLivro_Id(livroId, PageRequest.of(pagina, porPagina));
  }

  public Reserva criarReserva(Reserva r) {
    return this.repository.saveAndFlush(r);
  }

  public List<Reserva> listarTodos() {
    return this.repository.findAll();
  }
}
