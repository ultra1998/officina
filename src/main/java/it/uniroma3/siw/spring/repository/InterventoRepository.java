package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Intervento;


public interface InterventoRepository extends CrudRepository<Intervento, Long> {

	public List<Intervento> findByNome(String nome);
}