package br.com.ibm.consulting.bootcamp.demospring.service;

import br.com.ibm.consulting.bootcamp.demospring.domain.Livro;
import br.com.ibm.consulting.bootcamp.demospring.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExemplarService {
  @Autowired
  private LivroRepository repository;

  public Livro incrementarQuantidade(Livro livro, long quantidadeAdicionada, long quantidadeAntiga) {
    livro.setExemplar(quantidadeAntiga + quantidadeAdicionada);
    return this.repository.saveAndFlush(livro);
  }

  public Livro diminuirQuantidade(Livro livro, long quantidadeRemovida, long quantidadeAntiga) {
    livro.setExemplar(quantidadeAntiga - quantidadeRemovida);
    return this.repository.saveAndFlush(livro);
  }
}
