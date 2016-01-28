package com.ggsoft.poliglot.controller;

import com.ggsoft.poliglot.dto.WordDto;
import com.ggsoft.poliglot.dto.WordSPARQLSearchDTO;
import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.service.LanguageService;
import com.ggsoft.poliglot.service.MeaningService;
import com.ggsoft.poliglot.service.WordLinkService;
import com.ggsoft.poliglot.service.WordService;
import com.ggsoft.poliglot.sparqlservice.SPARQLService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("currentWord")
public class WordSPARQLController {

	private static final Logger logger = Logger.getLogger(WordSPARQLController.class);

	@Autowired
	WordService wordService;

	@Autowired
	LanguageService langService;

	@Autowired
	MeaningService meaningService;

	@Autowired
	WordLinkService wordLinkService;

	@Autowired
	MessageSource messageSource;

    @Autowired
	SPARQLService sparqlService;


    // For proper exception handling
	@ExceptionHandler(Exception.class)
	public void handleExceptions(Exception anExc) {
		anExc.printStackTrace(); // do something better than this ;)
	}

	@InitBinder("currentWord")
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields(new String[] { "id" });
	}

	@ModelAttribute("formLanguage")
	public List<Language> initializeLanguages() {
		return langService.findAllLanguages();
	}


	/**
	 * This method will provide the medium to search fors a new Word in a chosen
	 * language using sparql queries.
	 */
	@RequestMapping(value = { "/sparqlwords/wordsparqlsearch" }, method = RequestMethod.GET)
	public String newSearchWordInWiktionary( ModelMap model) {
		// Language lang = langService.findById(Integer.parseInt(langId));
		WordSPARQLSearchDTO nw = new WordSPARQLSearchDTO();

		model.addAttribute("searchWordSPARQL", nw);
		return "sparqlwords/wordsparqlsearch";
	}

	@RequestMapping(value = { "/sparqlwords/wordsparqlsearch" }, method = RequestMethod.POST)
	public String searchForWordInWiktionary( WordSPARQLSearchDTO  searchWordSPARQL,
			BindingResult result, RedirectAttributes redirectAttrs) {
        redirectAttrs.addFlashAttribute("searchWordSPARQL", searchWordSPARQL);
        return "redirect:/sparqlwords/listfoundsparqlwords";
	}

    @RequestMapping(value = { "/sparqlwords/listfoundsparqlwords" }, method = RequestMethod.GET)
    public String displayWordsFromWiktionary( @ModelAttribute("searchWordSPARQL")WordSPARQLSearchDTO  searchWordSPARQL, ModelMap model) {
        model.addAttribute("searchWordSPARQL", searchWordSPARQL);
        return "sparqlwords/wordslistsparql";
    }

	/**
	 * Method makes possible for angularjs to process wordDto object
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody WordDto index(@RequestParam("term") WordSPARQLSearchDTO searchWordSPARQL) {
        logger.info("\n Search word: *************  \n"+ searchWordSPARQL.getContent());
		WordDto wordW = this.sparqlService.findWordsWiktionary(searchWordSPARQL);
		return wordW;
	}

}
