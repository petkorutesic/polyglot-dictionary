package com.ggsoft.poliglot.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ggsoft.poliglot.converter.WordTypePropertyEditor;
import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.model.Word;
import com.ggsoft.poliglot.model.WordType;
import com.ggsoft.poliglot.service.LanguageService;
import com.ggsoft.poliglot.service.MeaningService;
import com.ggsoft.poliglot.service.WordService;
import com.ggsoft.poliglot.service.WordTypeService;

@Controller
@SessionAttributes(value={"newMeaning","formLanguage"})
public class MeaningController {
	private static final Logger logger = Logger.getLogger(MeaningController.class);
	
	@Autowired
	WordService wordService;

	@Autowired
	LanguageService langService;

	@Autowired
	MeaningService meaningService;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	WordTypeService wordTypeService;
	
	//I use ApplicationContex because autowiring is not possible in PropertyEditors
	@Autowired
    private ApplicationContext context;
	
	// For proper exception handling
	@ExceptionHandler(Exception.class)
	public void handleExceptions(Exception anExc) {
		anExc.printStackTrace(); // do something better than this ;)
	}
	@InitBinder("newMeaning")
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields(new String[] {"id", "word"});
        //We are using Property editors to avoid usage of Converters
		WordTypePropertyEditor editor = new WordTypePropertyEditor();
        context.getAutowireCapableBeanFactory().autowireBean(editor);
		dataBinder.registerCustomEditor(WordType.class, "wordTypes", editor);
    }


	@ModelAttribute("formLanguage")
	public List<Language> initializeLanguages() {
		return langService.findAllLanguages();
	}
	 
	// Spring MVC calls method loadWordWithMeaning(...) before newMeaningOfWord is called



	@RequestMapping(value = { "/meaning/edit-meaning-{meanId}" }, method = RequestMethod.GET)
	public String editMeaning(@PathVariable Integer meanId, ModelMap model) {
		Meaning meaning = meaningService.findById(meanId);
		model.addAttribute("newMeaning", meaning);
		model.addAttribute("edit", true);
		return "meanings/meaningregistration";
	}




	
	@RequestMapping(value = { "/meaning/new-meaning-forword-{wordId}" }, method = RequestMethod.GET)
	public String newMeaningOfWord(@PathVariable Integer wordId, ModelMap model) {
		Word w = wordService.findById(wordId);
		Meaning newMeaning = new Meaning();

		newMeaning.setLanguage(w.getLanguage());
		newMeaning.setWord(w);
		
		logger.info("Meaning word "+newMeaning.getWord().getContent() );
		logger.info("Meaning language   "+newMeaning.getLanguage().getLang() );	
		
		List<WordType> possibleWordTypes = wordTypeService.findAllWordTypes();
		
		
		
	    model.addAttribute("newMeaning",newMeaning);  
	    model.addAttribute("possibleWordTypes",possibleWordTypes); 
		model.addAttribute("edit", false);
		return "meanings/meaningregistration";
	}
	/*
	 * Saves a new meaning for of the specified word. 
	 * 
	 */

	@RequestMapping(value = { "/meaning/new-meaning-forword-{wordId}" }, method = RequestMethod.POST)
	public String saveMeaningOfWord(@ModelAttribute ("newMeaning") @Valid Meaning newMeaning, BindingResult result,
			@PathVariable Integer wordId, ModelMap model) {
		//Setting word for the new meaning according to the specified parameter in address bar.
		Word w = wordService.findById(wordId);
		newMeaning.setWord(w);
		
		if (result.hasErrors()) {
			model.addAttribute("newMeaning", newMeaning);
		
			List<FieldError> errors = result.getFieldErrors();
		    for (FieldError error : errors ) {
		        logger.info(error.getObjectName() + " - " + error.getDefaultMessage());
		    }
			return "meanings/meaningregistration";
		}
		meaningService.saveMeaning(newMeaning);

		model.addAttribute("success", "Meaning registered successfully");
		return "redirect:/words/" + wordId;
		
		
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating word in database. It also validates the word input
	 */
	@RequestMapping(value = { "/meaning/edit-meaning-{meanId}" }, method = RequestMethod.POST)
	public String updateMeaning(@Valid Meaning newMeaning , BindingResult result, ModelMap model, @PathVariable String meanId) {

		if (result.hasErrors()) {
			return "meanings/meaningregistration";
		}

		meaningService.updateMeaning(newMeaning);

		model.addAttribute("success", "Meaning " + newMeaning.getExplanation() + " updated successfully");
		return "redirect:/words/" + newMeaning.getWord().getId();
	}
	
	
	
	@RequestMapping(value = { "/meaning/delete-meaning-{minId}" }, method = RequestMethod.GET)
	public String deleteWord(@PathVariable Integer minId) {
		Meaning m = meaningService.findById(minId);
		Word w = m.getWord();
		meaningService.deleteMeaning(m);
		
		return "redirect:/words/" + w.getId();

	}


}
