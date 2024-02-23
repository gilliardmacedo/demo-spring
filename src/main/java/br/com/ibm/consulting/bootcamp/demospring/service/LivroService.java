package br.com.ibm.consulting.bootcamp.demospring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import br.com.ibm.consulting.bootcamp.demospring.repository.LivroRepository;

@Component
public class LivroService {
	
	@Autowired
	LivroRepository repository;
	
	public Livro criarLivro(Livro l) {
		return repository.saveAndFlush(l);
	}
	
	public List<Livro> listar() {
		return repository.findAll();
	}
	
	public Livro obter(long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void alterar(long id, Livro novoLivro) {
		var alterado = new Livro(id, novoLivro.getAutor(), novoLivro.getTitulo(), novoLivro.getAnoPublicacao());
		repository.saveAndFlush(alterado);
	}
	
	public void excluir(long id) {
		var excluir = new Livro(id, null, null, null);
		repository.delete(excluir);
	}


}
