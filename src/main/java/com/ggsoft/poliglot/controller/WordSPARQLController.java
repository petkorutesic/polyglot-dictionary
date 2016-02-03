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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
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

	/**
	 * Method makes possible for angularjs to process wordDto object
	 *
	 * @param
	 * @return
	 */

	@RequestMapping(value = "/sparql/wordsearch", method = RequestMethod.POST)
	public ResponseEntity<WordDto> listAllUsers(@RequestBody  WordSPARQLSearchDTO wordSearch) {
		WordDto wordW = this.sparqlService.findWordsWiktionary(wordSearch);
		return new ResponseEntity<WordDto>(wordW, HttpStatus.OK);
	}
	/**
	 * Method saves word from single page angular applicaton
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/sparql/wordsave", method = RequestMethod.POST)
	public ResponseEntity<Integer> saveWord(@RequestBody  WordDto wordDto) {
		Integer wordId = this.sparqlService.saveWord(wordDto);
		return new ResponseEntity<Integer>(wordId, HttpStatus.OK);
	}

}
