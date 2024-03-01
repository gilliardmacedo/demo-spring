package br.com.ibm.consulting.bootcamp.demospring.controller;

import br.com.ibm.consulting.bootcamp.demospring.domain.Exemplar;
import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import br.com.ibm.consulting.bootcamp.demospring.domain.Reserva;
import br.com.ibm.consulting.bootcamp.demospring.service.ExemplarService;
import br.com.ibm.consulting.bootcamp.demospring.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/livros/{livroId}/exemplares")
public class ExemplarController {
  @Autowired
  private ExemplarService service;
  @Autowired
  private LivroService livroService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Livro> incrementarExemplar(@RequestBody Exemplar exemplar, @PathVariable long livroId) {
    var livro = livroService.obter(livroId);
    if (livro == null) return new ResponseEntity<Livro>(livro, HttpStatus.NOT_FOUND);
    var alterado = service.incrementarQuantidade(livro, exemplar.getQuantidade(), livro.getExemplar());
    return new ResponseEntity<Livro>(alterado, HttpStatus.CREATED);
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Livro> diminuirExemplar(@RequestBody Exemplar exemplar, @PathVariable long livroId) {
    var livro = livroService.obter(livroId);
    if (livro == null) return new ResponseEntity<Livro>(livro, HttpStatus.NOT_FOUND);
    if (livro.getExemplar() < exemplar.getQuantidade()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Essa quantia é maior do que a quantidade de exemplares disponíveis");
    var alterado = service.diminuirQuantidade(livro, exemplar.getQuantidade(), livro.getExemplar());
    return new ResponseEntity<Livro>(alterado, HttpStatus.CREATED);
  }

}
