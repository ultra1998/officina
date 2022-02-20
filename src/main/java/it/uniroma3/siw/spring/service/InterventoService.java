package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Intervento;
import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.repository.InterventoRepository;


@Service
public class InterventoService {
	
	
	@Autowired
	private InterventoRepository interventoRepository; 
	
	@Autowired
	private MeccanicoService meccanicoService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@Autowired
	private UserService userService;
	
	
	public MeccanicoService getMeccanicoService() {
		return meccanicoService;
	}
	
	public UserService getUserService() {
		return userService;
	}
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

	public void setCredentialsService(CredentialsService credentialsService) {
		this.credentialsService = credentialsService;
	}

	public PrenotazioneService getPrenotazioneService() {
		return prenotazioneService;
	}

	@Transactional
	public Intervento inserisci(Intervento intervento) {
		return interventoRepository.save(intervento);
	}
	
	@Transactional
	public List<Intervento> interventoPerNome(String nome ) {
		return interventoRepository.findByNome( nome);
	}

	@Transactional
	public List<Intervento> tutti() {
		return (List<Intervento>) interventoRepository.findAll();
	}

	@Transactional
	public Intervento interventoPerId(Long id) {
		Optional<Intervento> optional = interventoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Intervento intervento) {
		List<Intervento> interventi = this.interventoRepository.findByNome(intervento.getNome());
		if (interventi.size() > 0)
			return true;
		else 
			return false;
	}
	
	
	@Transactional
	public List<Intervento> filtraLista(List<Intervento> lista) {
		List<Intervento> interventi=this.tutti();
		for(Intervento i:lista) {	
			interventi.remove(i);
		}
		return interventi;
	}
	
	@Transactional
	public void eliminaIntervento(Intervento i) {
		interventoRepository.delete(i);
	}
	
	@Transactional
	public List<Intervento> getInterventiFiltered(){
		List<Intervento> filtrato=new ArrayList<>();
		for(Intervento i: this.tutti()) {
			if(i.getTipologiaIntervento()==null)
				filtrato.add(i);
		}
		return filtrato;
	}

	@Transactional
	public List<Prenotazione> getPrenotazioniIntervento(Intervento i){
		List<Prenotazione> lista = new ArrayList<>();
		for(Prenotazione p: prenotazioneService.tutti()) {
			if(p.getIntervento()== i)
				lista.add(p);
		}
		return lista;
	}
	

}