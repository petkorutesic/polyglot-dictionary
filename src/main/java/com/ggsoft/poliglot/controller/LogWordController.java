package com.ggsoft.poliglot.controller;

import com.ggsoft.poliglot.converter.FormLanguageToLanguageEditor;
import com.ggsoft.poliglot.dto.WordSearchDTO;
import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.model.LogWord;
import com.ggsoft.poliglot.model.Word;
import com.ggsoft.poliglot.service.LanguageService;
import com.ggsoft.poliglot.service.LogWordService;
import com.ggsoft.poliglot.service.WordService;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes(value={"currentWord"})
public class LogWordController {

	private static final Logger logger = Logger.getLogger(LogWordController.class);

	@Autowired
	WordService wordService;


	@Autowired
	LogWordService logWordService;


    // For proper exception handling
	@ExceptionHandler(Exception.class)
	public void handleExceptions(Exception anExc) {
		anExc.printStackTrace(); // do something better than this ;)
	}

	// Custom handler for displaying a log of visits of the specified word
	@RequestMapping(value = { "/wordlogs/wordlogs-for-word-{wordId}" }, method = RequestMethod.GET)
	public String showWord(@PathVariable("wordId") Integer wordId, ModelMap model) {

		Word w = wordService.findById(wordId);
		model.addAttribute("currentWord", w);
        List<LogWord> logs= logWordService.findLogsForWord(w);
        model.addAttribute("wordLogs", logs);
		return "wordlogs/wordlogdetails";
	}

	@RequestMapping(value = { "/wordlogs/delete-wordlogs-forword-{wordId}" }, method = RequestMethod.GET)
	public String deleteLogWord(@PathVariable Integer wordId) {
		Word currentWord = wordService.findById(wordId);
		logWordService.deleteLogsForWord(currentWord);

		return "redirect:/wordlogs/wordlogs-for-word-" + currentWord.getId();

	}

}
