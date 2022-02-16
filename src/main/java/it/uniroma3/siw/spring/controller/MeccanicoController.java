package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.MeccanicoValidator;
import it.uniroma3.siw.spring.model.Intervento;
import it.uniroma3.siw.spring.model.Meccanico;
import it.uniroma3.siw.spring.service.MeccanicoService;


@Controller
public class MeccanicoController {
	
	@Autowired
	private MeccanicoService meccanicoService;
	
    @Autowired
    private MeccanicoValidator meccanicoValidator;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    

    @RequestMapping(value="/admin/addMeccanico", method = RequestMethod.GET)
    public String addMeccanico(Model model) {
    	logger.debug("addMeccanico");
    	model.addAttribute("meccanico", new Meccanico());
        return "meccanicoForm.html";
    }

    @RequestMapping(value = "/meccanico/{id}", method = RequestMethod.GET)
    public String getMeccanico(@PathVariable("id") Long id, Model model) {
    	Meccanico m=this.meccanicoService.meccanicoPerId(id);
    	model.addAttribute("meccanico", m);
    	model.addAttribute("interventiMeccanico",m.getInterventi());
    	return "meccanico.html";
    }

    @RequestMapping(value = "/meccanico", method = RequestMethod.GET)
    public String getMeccanici(Model model) {
    		model.addAttribute("meccanici", this.meccanicoService.tutti());
    		return "meccanici.html";
    }
    
    @RequestMapping(value = "/admin/meccanico", method = RequestMethod.POST)
    public String newMeccanico(@ModelAttribute("meccanico") Meccanico meccanico, 
    									Model model, BindingResult bindingResult) {
    	this.meccanicoValidator.validate(meccanico, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.meccanicoService.inserisci(meccanico);
            model.addAttribute("meccanici", this.meccanicoService.tutti());
            return "meccanici.html";
        }
        return "meccanicoForm.html";
    }
    
    

    @RequestMapping(value = "/admin/eliminaMeccanico/{id}", method = RequestMethod.POST)
    public String eliminaMeccanico(Model model, @PathVariable("id") Long idMeccanico) {
    		
    		Meccanico m=meccanicoService.meccanicoPerId(idMeccanico);
    		meccanicoService.eliminaMeccanico(m);
    		model.addAttribute("meccanico", this.meccanicoService.tutti());
    		return "meccanici.html";
    }
    
    
}
