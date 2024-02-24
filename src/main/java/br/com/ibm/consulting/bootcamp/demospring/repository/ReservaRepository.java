package br.com.ibm.consulting.bootcamp.demospring.repository;

import br.com.ibm.consulting.bootcamp.demospring.domain.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
  void deleteByLivro_Id(long livroId);
  Page<Reserva> findByLivro_Id(long livroId, Pageable pageable);
}
