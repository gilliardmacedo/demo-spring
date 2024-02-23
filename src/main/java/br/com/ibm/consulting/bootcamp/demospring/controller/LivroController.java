package br.com.ibm.consulting.bootcamp.demospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public List<Livro> listar() {
		return service.listar();
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
