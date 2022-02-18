package it.uniroma3.siw.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Intervento;
import it.uniroma3.siw.spring.model.Prenotazione;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long> {

	public List<Prenotazione> findById(String idPrenotazione);
	
	public List<Prenotazione> findByDataPrenotazioneAndOraPrenotazione(Date dataPrenotazione, int oraPrenotazione);

	public List<Prenotazione> findByDataPrenotazioneAndOraPrenotazioneAndIntervento(java.sql.Date dataPrenotazione,
			int oraPrenotazione, Intervento intervento);
	
	public List<Prenotazione> findByDataPrenotazioneAndOraPrenotazioneAndIntervento(Date dataPrenotazione, int oraPrenotazione, Intervento i);
}