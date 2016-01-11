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

import com.ggsoft.poliglot.converter.UsageTypePropertyEditor;
import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.model.Usage;
import com.ggsoft.poliglot.model.UsageType;
import com.ggsoft.poliglot.service.MeaningService;
import com.ggsoft.poliglot.service.UsageService;
import com.ggsoft.poliglot.service.UsageTypeService;


@Controller
@SessionAttributes(value={"usageType"})
public class UsageController {
	private static final Logger logger = Logger.getLogger(UsageController.class);
	
	@Autowired
	UsageService usageService;
	
	@Autowired
	UsageTypeService usageTypeService;


	@Autowired
	MeaningService meaningService;

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
	@InitBinder("newUsage")
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields(new String[] {"id"});
        //We are using Property editors to avoid usage of Converters
		UsageTypePropertyEditor editor = new UsageTypePropertyEditor();
        context.getAutowireCapableBeanFactory().autowireBean(editor);
		dataBinder.registerCustomEditor(UsageType.class, "usageTypes", editor);
    }



	@RequestMapping(value = { "/wordusages/edit-wordusage-{usageId}" }, method = RequestMethod.GET)
	public String editWordUsage(@PathVariable Integer usageId, ModelMap model) {
		Usage usage = usageService.findById(usageId);
		model.addAttribute("newUsage", usage);
		model.addAttribute("edit", true);
		return "wordusages/usageregistration";
	}




	
	@RequestMapping(value = { "/wordusages/new-usage-for-meaning-{meaningId}" }, method = RequestMethod.GET)
	public String newUsageOfWord(@PathVariable Integer meaningId, ModelMap model) {
		Meaning m = meaningService.findById(meaningId);
		Usage newUsage = new Usage();
		
		logger.info("Usage for the meaning "+ m.getExplanation() );
		
		List<UsageType> possibleUsageTypes = usageTypeService.findAllUsageTypes();
		
		
		model.addAttribute("meaning",m);
	    model.addAttribute("newUsage",newUsage);  
	    model.addAttribute("possibleWordTypes",possibleUsageTypes); 
		model.addAttribute("edit", false);
		return "wordusages/usageregistration";
	}
	/*
	 * Saves a new meaning for of the specified word. 
	 * 
	 */

	@RequestMapping(value = { "/wordusages/new-usage-for-meaning-{meaningId}" }, method = RequestMethod.POST)
	public String saveUsageOfWord(@ModelAttribute ("newUsage") @Valid Usage newUsage, BindingResult result,
			@PathVariable Integer meaningId, ModelMap model) {
		//Setting word for the new meaning according to the specified parameter in address bar.
		Meaning meaning = meaningService.findById(meaningId);

		if (result.hasErrors()) {
			model.addAttribute("newUsage", newUsage);
		
			List<FieldError> errors = result.getFieldErrors();
		    for (FieldError error : errors ) {
		        logger.info(error.getObjectName() + " - " + error.getDefaultMessage());
		    }
			return "meanings/meaningregistration";
		}
		usageService.saveUsage(newUsage);
		
		//Adding new usage example to the meaning list
		meaning.getWordUsages().add(newUsage);
		meaningService.updateMeaning(meaning);

		model.addAttribute("success", "Example of word usage registered successfully");
		return "redirect:/words/" + meaning.getWord().getId();
		
		
	}
	
	@RequestMapping(value = { "/wordusages/edit-wordusage-{usageId}" }, method = RequestMethod.POST)
	public String updateWordUsage(@Valid Usage wordUsage , BindingResult result, ModelMap model, @PathVariable String usageId) {

		if (result.hasErrors()) {
			return "meanings/meaningregistration";
		}

		usageService.updateUsage(wordUsage);

		model.addAttribute("success", "Meaning " + wordUsage.getId() + " updated successfully");
		return "redirect:/words/" + wordUsage.getId();
	}
	
	
	
	@RequestMapping(value = { "/wordusages/delete-wordusage-{usageId}" }, method = RequestMethod.GET)
	public String deleteWordUsage(@PathVariable Integer usageId) {
		Usage u = usageService.findById(usageId);
		usageService.deleteUsage(u);
		
		return "redirect:/words/" + u.getId();

	}


}
