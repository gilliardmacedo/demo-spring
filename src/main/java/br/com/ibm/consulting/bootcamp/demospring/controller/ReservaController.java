package br.com.ibm.consulting.bootcamp.demospring.controller;

import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import br.com.ibm.consulting.bootcamp.demospring.domain.Reserva;
import br.com.ibm.consulting.bootcamp.demospring.service.LivroService;
import br.com.ibm.consulting.bootcamp.demospring.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/livros/{livroId}/reservas")
public class ReservaController {
  @Autowired
  ReservaService service;
  @Autowired
  LivroService livroService;

  @GetMapping
  public ResponseEntity<Page<Reserva>> listar(
      @PathVariable long livroId,
      @RequestParam(value = "page", required = false) Integer pagina,
      @RequestParam(value = "tamanho-pagina", required = false) Integer tamanhoPagina
  ) {
    Page<Reserva> reservas = service.listarReservasPorLivro(livroId, pagina, tamanhoPagina);
    return new ResponseEntity<Page<Reserva>>(reservas, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Reserva>> listarTodos() {
    return new ResponseEntity<List<Reserva>>(this.service.listarTodos(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Reserva> criar(@PathVariable long livroId, @RequestBody Reserva reservaBody) {
    Livro livro = livroService.obter(livroId);
    if (livro == null)
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Livro de id %s não existe", Long.toString(livroId)));
    Reserva reserva = this.service.criarReserva(new Reserva(livro, reservaBody.getUsuario(), reservaBody.getDataInicio(), reservaBody.getDataFim()));
    return new ResponseEntity<Reserva>(reserva, HttpStatus.CREATED);
  }
}
