package br.com.ibm.consulting.bootcamp.demospring.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import br.com.ibm.consulting.bootcamp.demospring.service.LivroService;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

  @Autowired
  LivroService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Livro criar(@RequestBody Livro livro) {
    return service.criarLivro(livro);
  }

  @GetMapping
  public Page<Livro> listar(
      @RequestParam(value = "page", required = false) Integer pagina,
      @RequestParam(value = "tamanho-pagina", required = false) Integer tamanhoPagina
  ) {
    return service.listar(pagina, tamanhoPagina);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Livro> obter(@PathVariable long id) {
    var livro = service.obter(id);
    if (livro != null) {
      return new ResponseEntity<Livro>(livro, HttpStatus.OK);
    }
    return new ResponseEntity<Livro>(livro, HttpStatus.NOT_FOUND);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable long id) {
    var livroExistente = service.obter(id);
    if (livroExistente != null) {
      service.alterar(id, livro);
      return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Void> excluir(@PathVariable long id) {
    var livroExistente = service.obter(id);
    if (livroExistente != null) {
      service.excluir(id);
      return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
  }
}
