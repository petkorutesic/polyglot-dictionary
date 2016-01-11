package com.ggsoft.poliglot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.model.Word;
import com.ggsoft.poliglot.service.LanguageService;
import com.ggsoft.poliglot.service.MeaningService;
import com.ggsoft.poliglot.service.WordLinkService;
import com.ggsoft.poliglot.service.WordService;

@Controller
@SessionAttributes("currentWord")
public class WordController {

	public static final Logger logger = Logger.getLogger(WordController.class);

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
	 * This method will list all existing Words.
	 */
	@RequestMapping(value = { "/listwords" }, method = RequestMethod.GET)
	public String listWords(ModelMap model) {
		List<Word> words = wordService.findAllWords();
		model.addAttribute("words", words);
		return "words/wordslist";
	}

	// get list of words for auto completion
	@RequestMapping(value = "/words/searchword_list", method = RequestMethod.GET)
	public @ResponseBody List<Word> getSearchWordList(@RequestParam("term") String query) {
		//it must be the name term it's default for source in java script 
		List<Word> wordList = wordService.findByWord(query);

		return wordList;
	}

	@RequestMapping(value = { "/words/words-inlang-{lanId}" }, method = RequestMethod.GET)
	public String listLanguageWords(@PathVariable String lanId, ModelMap model) {
		Language l = langService.findById(Integer.parseInt(lanId));
		List<Word> ws = new ArrayList<Word>(l.getLanguageWords());
		model.addAttribute("words", ws);
		model.addAttribute("language", l);
		return "words/wordslist";
	}

	// Custom handler for displaying an word
	@RequestMapping(value = { "/words/{wordId}" })
	public String showWord(@PathVariable("wordId") Integer wordId, ModelMap model) {

		// Word w = wordService.findByIdComplete(wordId);
		Word w = wordService.findByIdComplete(wordId);
		logger.info("\n ****** Reading information for the language ****** " + w.getLanguage().getLang() + "\n");
		model.addAttribute("currentWord", w);
		return "words/worddetails";
	}

	@RequestMapping(value = { "/words/find" }, method = RequestMethod.GET)
	public String initFindForm(ModelMap model) {
		model.addAttribute("currentWord", new Word());
		return "words/wordfind";
	}

	@RequestMapping(value = { "/words" })
	public String processFindForm(Word currentWord, BindingResult result, ModelMap model) {
		// allow parameterless GET request for /owners to return all records
		if (currentWord.getContent() == null) {
			currentWord.setContent(""); // empty string signifies broadest
										// possible
			// search
		}

		// find owners by last name
		List<Word> results = this.wordService.findByWord(currentWord.getContent());
		for (Word k : results) {
			System.out.println(k.getContent() + "\n");
		}
		if (results.isEmpty()) {
			logger.info("***************** No word found *****************\n");// no owners found
			result.rejectValue("currentWord", "notFound", "not found");
			return "redirect:/words/find";
		} else if (results.size() == 1) {
			// 1 word found
			currentWord = results.iterator().next();
			return "redirect:/words/" + currentWord.getId();
		} else {
			// multiple owners found
			model.addAttribute("words", results);
			return "words/wordslist";
		}
	}

	/**
	 * This method will provide the medium to add a new Word in a chosen
	 * language.
	 */
	@RequestMapping(value = { "/words/newword-inlang-{langId}" }, method = RequestMethod.GET)
	public String newWordInLanguage(@PathVariable String langId, ModelMap model) {
		// Language lang = langService.findById(Integer.parseInt(langId));
		Word nw = new Word();

		DateTime currentTime = new DateTime();
		nw.setTimeCreation(currentTime);
		if (! langId.isEmpty()) {
			Language lang = langService.findById(Integer.parseInt(langId));
			nw.setLanguage(lang);
		}

		model.addAttribute("currentWord", nw);
		model.addAttribute("edit", false);
		return "words/wordregistration";
	}

	@RequestMapping(value = { "/words/newword-inlang-{langId}" }, method = RequestMethod.POST)
	public String saveWordInLanguage(@ModelAttribute("currentWord") @Valid Word currentWord, BindingResult result,
			ModelMap model, @PathVariable String langId) {
		DateTime currentTime = new DateTime();
		currentWord.setTimeCreation(currentTime);
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {
				logger.info("Object " + error.getObjectName() + " - Field " + error.getField() + " - "
						+ error.getDefaultMessage());
			}
			return "words/wordregistration";
		}
		wordService.saveWord(currentWord);

		model.addAttribute("success",
				"Word " + currentWord.getContent() + " " + currentWord.getTimeCreation() + " registered successfully");
		// Automatically jumps on the form for a new meaning
		return "redirect:/meaning/new-meaning-forword-" + currentWord.getId();
	}

	/**
	 * This method will provide the medium to update an existing word.
	 */
	@RequestMapping(value = { "/words/edit-word-{wordId}" }, method = RequestMethod.GET)
	public String editWord(@PathVariable Integer wordId, ModelMap model) {
		Word word = wordService.findById(wordId);
		
		model.addAttribute("currentWord", word);
		model.addAttribute("edit", true);
		return "words/wordregistration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating word in database. It also validates the word input
	 */
	@RequestMapping(value = { "/words/edit-word-{wordId}" }, method = RequestMethod.POST)
	public String updateWord(@Valid Word currentWord, BindingResult result, ModelMap model,
			@PathVariable String wordId) {

		if (result.hasErrors()) {
			model.addAttribute("currentWord", currentWord);
			logger.info("\n ********** Validation error for word " + currentWord + "  *********\n");
			List<FieldError> errors = result.getFieldErrors();
		    for (FieldError error : errors ) {
		        logger.info(error.getObjectName().getClass()+ " - " + error.getDefaultMessage());
		    }
			return "words/wordregistration";
		}

		wordService.updateWord(currentWord);

		model.addAttribute("success", "Word " + currentWord.getContent() + " updated successfully");
		return "redirect:/words/" + currentWord.getId();
	}

	@RequestMapping(value = { "/words/delete-word-{wordId}" }, method = RequestMethod.GET)
	public String deleteWord(@PathVariable Integer wordId) {
		Word w = wordService.findById(wordId);
		wordService.deleteWord(w);

		return "redirect:/words/words-inlang-" + w.getLanguage().getId();

	}

}
