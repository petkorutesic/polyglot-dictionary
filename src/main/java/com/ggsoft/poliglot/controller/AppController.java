package com.ggsoft.poliglot.controller;

import java.util.List;

import com.ggsoft.poliglot.sparqldao.SPARQLDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.service.LanguageService;
import com.ggsoft.poliglot.service.MeaningService;
import com.ggsoft.poliglot.service.WordService;

@Controller
@RequestMapping("/")


public class AppController {

    @Autowired
    WordService wordService;

    @Autowired
    LanguageService langService;

    @Autowired
    MeaningService meaningService;

    @Autowired
    MessageSource messageSource;

    // For proper exception handling
    @ExceptionHandler(Exception.class)
    public void handleExceptions(Exception anExc) {
        anExc.printStackTrace(); // do something better than this ;)
    }


    /**
     * Starting page.
     */
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(ModelMap model) {

        return "welcome";
    }


    /**
     * This method will list all existing languages.
     */
    @RequestMapping(value = { "/listlanguages"}, method = RequestMethod.GET)
    public String listLanguages(ModelMap model) {

        List<Language> langs = langService.findAllLanguages();
        model.addAttribute("languages", langs);
        return "languages/langslist";
    }



}
