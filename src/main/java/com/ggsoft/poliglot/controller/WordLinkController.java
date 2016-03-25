package com.ggsoft.poliglot.controller;

import java.util.List;

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

import com.ggsoft.poliglot.converter.MeaningPropertyEditor;
import com.ggsoft.poliglot.model.LinkType;
import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.model.Word;
import com.ggsoft.poliglot.model.WordLink;
import com.ggsoft.poliglot.service.LinkTypeService;
import com.ggsoft.poliglot.service.MeaningService;
import com.ggsoft.poliglot.service.WordLinkService;
import com.ggsoft.poliglot.service.WordService;

@Controller
@SessionAttributes(value = { "newWordLink", "allLinkTypes", "allMeanings"})
public class WordLinkController {
	private Logger logger = Logger.getLogger(WordLinkController.class);

	@Autowired
	WordLinkService wordLinkService;

	@Autowired
	LinkTypeService linkTypeService;

	@Autowired
	MeaningService meaningService;
	
	@Autowired
	WordService wordService;

	@Autowired
	MessageSource messageSource;
	
	//I use ApplicationContex because autowiring is not possible in PropertyEditors
	@Autowired
    private ApplicationContext context;

	// For proper exception handling
	@ExceptionHandler(Exception.class)
	public void handleExceptions(Exception anExc) {
		anExc.printStackTrace(); // do something better than this ;)
	}

	@InitBinder("newWordLink")
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields(new String[] { "id", "meaningFrom"});
		MeaningPropertyEditor editor = new MeaningPropertyEditor();
        context.getAutowireCapableBeanFactory().autowireBean(editor);
		dataBinder.registerCustomEditor(Meaning.class, "meaningTo", editor);
	}

	@ModelAttribute("allLinkTypes")
	public List<LinkType> initializeLynkTypes() {
		return linkTypeService.findAllLinkTypes();
	}
	
	@RequestMapping(value = { "/wordlinks/meaning-{meaningId}/word-{wordId}" }, method = RequestMethod.GET)
	public String listWordLinks(@PathVariable Integer meaningId, @PathVariable Integer wordId, ModelMap model) {
		Meaning fromMeaning = meaningService.findById(meaningId);
		WordLink newWordLink = new WordLink();
		
		LinkType link = linkTypeService.findById(1);
		newWordLink.setLinkType(link);
		newWordLink.setMeaningFrom(fromMeaning);
		
		//Meanings of the selected word 
		Word w = wordService.findById(wordId);
		List<Meaning> possibleMeaningsTo = meaningService.findMeaningsForWord(w);
		
		model.addAttribute("newWordLink", newWordLink);
		model.addAttribute("possibleMeaningsTo", possibleMeaningsTo);
		model.addAttribute("edit", false);
		return "wordlinks/wordlinkdetails";
	}
	
	//Inserts a new meaning which is the meaning of specified word
	@RequestMapping(value = { "/wordlinks/meaning-{meaningId}/word-{wordId}" }, method = RequestMethod.POST)
	public String addNewLink(ModelMap model,  @PathVariable String meaningId, 
			@ModelAttribute("newWordLink") WordLink newWordLink, BindingResult result) {
		
	
		
		if (result.hasErrors()) {
			logger.info("\n *************   Something is wrong *************  \n");
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				logger.info("Object " + error.getObjectName() + " - Field " + error.getField() + " - "
						+ error.getDefaultMessage() + "\n" + "Rejected value "+ error.getRejectedValue());
			}
			return "wordlinks/wordlinkdetails";
		}
		logger.info("Saving wordlink " + newWordLink.toString());
		wordLinkService.saveWordLink(newWordLink);

		model.addAttribute("success", "WordLink " + newWordLink.getId() + " updated successfully");
		return "redirect:/wordlinks/meaning-" + meaningId +"/search";
	}
	
	@RequestMapping(value = { "/wordlinks/meaning-{meaningId}/search" }, method = RequestMethod.GET)
	public String searchWordForWordLinks(@PathVariable Integer meaningId, ModelMap model) {
		Meaning fromMeaning = meaningService.findById(meaningId);
		
		model.addAttribute("fromMeaning", fromMeaning);
		return "wordlinks/wordssearch";
	}
	
	@RequestMapping(value = { "/wordlinks/meaning-{meaningId}/delete-wordlink-{wordLinkId}" }, method = RequestMethod.GET)
	public String deleteWordLink(ModelMap model, @PathVariable Integer meaningId, @PathVariable Integer wordLinkId) {
        WordLink wl = wordLinkService.findById(wordLinkId);
		wordLinkService.deleteWordLink(wl);
		
	//	Meaning fromMeaning = meaningService.findById(meaningId);
		return "redirect:/wordlinks/meaning-" + meaningId +"/search";

	}
	
		
}


