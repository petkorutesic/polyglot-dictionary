package com.ggsoft.poliglot.controller;

import com.ggsoft.poliglot.converter.FormLanguageToLanguageEditor;
import com.ggsoft.poliglot.dto.WordSearchDTO;
import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.model.Word;
import com.ggsoft.poliglot.service.LanguageService;
import com.ggsoft.poliglot.service.LogWordService;
import com.ggsoft.poliglot.service.WordService;
import com.ggsoft.poliglot.utils.ExternalWordLink;
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
import java.util.*;

@Controller
@SessionAttributes(value = {"currentWord"})
public class WordController {

    private static final Logger logger = Logger.getLogger(WordController.class);

    @Autowired
    WordService wordService;

    @Autowired
    LanguageService langService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    LogWordService logWordService;

    //I use ApplicationContex because autowiring is not possible in PropertyEditors
    @Autowired
    private ApplicationContext context;

    // For proper exception handling
    @ExceptionHandler(Exception.class)
    public void handleExceptions(Exception anExc) {
        anExc.printStackTrace(); // do something better than this ;)
    }

    @InitBinder("searchWord")
    public void setAllowedSearchFields(WebDataBinder dataBinder) {
        //We are using Property editors to avoid usage of Converters
        FormLanguageToLanguageEditor editor = new FormLanguageToLanguageEditor();
        context.getAutowireCapableBeanFactory().autowireBean(editor);
        dataBinder.registerCustomEditor(WordSearchDTO.class, "languages", editor);
    }

    @InitBinder("currentWord")
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields(new String[]{"id"});
    }

    /**
     * This method will list all existing Words.
     */
    @RequestMapping(value = {"/listwords"}, method = RequestMethod.GET)
    public String listWords(ModelMap model) {
        List<Word> words = wordService.findAllWords();
        model.addAttribute("words", words);
        return "words/wordslist";
    }

    /**
     * This method provides information for the sake of auto completion
     */
    @RequestMapping(value = "/words/searchword_list", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Word> getSearchWordList(@RequestParam("term") String query) {
        //it must be the name term it's default for source in java script
        List<Word> wordList = wordService.findByWord(query);

        return wordList;
    }


    @RequestMapping(value = {"/words/words-inlang-{lanId}"}, method = RequestMethod.GET)
    public String listLanguageWords(@PathVariable String lanId, ModelMap model) {
        Language l = langService.findById(Integer.parseInt(lanId));
        List<Word> ws = new ArrayList<Word>(l.getLanguageWords());
        model.addAttribute("words", ws);
        model.addAttribute("language", l);
        return "words/wordslist";
    }

    // Custom handler for displaying an word
    @RequestMapping(value = {"/words/{wordId}"})
    public String showWord(@PathVariable("wordId") Integer wordId, ModelMap model) {

        // Word w = wordService.findByIdComplete(wordId);
        Word w = wordService.findByIdComplete(wordId);
        model.addAttribute("currentWord", w);
        List<ExternalWordLink> externalLinks = wordService.findExternalLinks(w);
        model.addAttribute("externalLinks", externalLinks);
        logWordService.updateLogForWord(w);
        return "words/worddetails";
    }

    @RequestMapping(value = {"/words/find"}, method = RequestMethod.GET)
    public String initFindForm(ModelMap model, Locale locale) {
        model.addAttribute("searchWord", new WordSearchDTO());
        Map<WordSearchDTO.WORD_SEARCH_MODE, String> searchModels = new HashMap<>();

        for (WordSearchDTO.WORD_SEARCH_MODE search_mode : WordSearchDTO.WORD_SEARCH_MODE.getAllSearchModes()) {
            searchModels.put(search_mode,
                    messageSource.getMessage("Search.Mode." + search_mode.getWordSearchCode() + ".string", null, locale));
        }

        model.addAttribute("searchModels", searchModels);
        model.addAttribute("searchLanguages", langService.findAllLanguages());

        return "words/wordfind";
    }

    @RequestMapping(value = {"/words"})
    public String processFindForm(@ModelAttribute("searchWord") @Valid WordSearchDTO searchWord,
                                  BindingResult result, ModelMap model, Locale locale) {
        if (result.hasErrors()) {
            //Adding the same searchmodels again
            //	Map<String, String> searchModels = new HashMap<String, String>();
            Map<WordSearchDTO.WORD_SEARCH_MODE, String> searchModels = new HashMap<>();

            for (WordSearchDTO.WORD_SEARCH_MODE search_mode : WordSearchDTO.WORD_SEARCH_MODE.getAllSearchModes()) {
                searchModels.put(search_mode,
                        messageSource.getMessage("Search.Mode." + search_mode.getWordSearchCode() + ".string", null, locale));
            }


            model.addAttribute("searchModels", searchModels);
            return "words/wordfind";
        }

        // find words by their content
        List<Word> results = this.wordService.findWordsGeneral(searchWord);
        for (Word k : results) {
            logger.info(k.getContent() + "\n");
        }
        if (results.isEmpty()) {
            logger.info("***************** No word found *****************\n");// no owners found
            logger.info("**********Search word*********" + searchWord.toString());
            result.rejectValue("content", "notFound");
            return "redirect:/words/find";
        } else if (results.size() == 1) {
            // 1 word found
            Word currentWord = results.iterator().next();
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
    @RequestMapping(value = {"/words/newword-inlang-{langId}"}, method = RequestMethod.GET)
    public String newWordInLanguage(@PathVariable String langId, ModelMap model) {
        // Language lang = langService.findById(Integer.parseInt(langId));
        Word nw = new Word();

        DateTime currentTime = new DateTime();
        nw.setTimeCreation(currentTime);
        if (!langId.isEmpty()) {
            Language lang = langService.findById(Integer.parseInt(langId));
            nw.setLanguage(lang);
        }
        model.addAttribute("formLanguage", langService.findAllLanguages());
        model.addAttribute("currentWord", nw);
        model.addAttribute("edit", false);
        return "words/wordregistration";
    }


    @RequestMapping(value = {"/words/newword-inlang-{langId}"}, method = RequestMethod.POST)
    public String saveWordInLanguage(@ModelAttribute("currentWord") @Valid Word currentWord, BindingResult result,
                                     ModelMap model, @PathVariable String langId, @RequestParam(value = "wordAction") String saveOrSearch) {
        DateTime currentTime = new DateTime();
        currentWord.setTimeCreation(currentTime);
        String returnValue = "";
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                logger.info("Object " + error.getObjectName() + " - Field " + error.getField() + " - "
                        + error.getDefaultMessage());
            }
            model.addAttribute("formLanguage", langService.findAllLanguages());
            returnValue = "words/wordregistration";
        }
        if (saveOrSearch.equalsIgnoreCase("Save")) {
            wordService.saveWord(currentWord);

            model.addAttribute("success",
                    "Word " + currentWord.getContent() + " " + currentWord.getTimeCreation() + " registered successfully");
            // Automatically jumps on the form for a new meaning
            returnValue = "redirect:/meaning/new-meaning-forword-" + currentWord.getId();

        } else if (saveOrSearch.equalsIgnoreCase("Search")) {
            //TODO This can be implemented in better way
            WordSearchDTO searchWord= new WordSearchDTO();
            searchWord.setContent(currentWord.getContent());
            searchWord.setSearchMode(WordSearchDTO.WORD_SEARCH_MODE.SIMPLE);

            List<Word> results = this.wordService.findWordsGeneral(searchWord);
            for (Word k : results) {
                logger.info(k.getContent() + "\n");
            }
            if (results.isEmpty()) {
/*                result.rejectValue("content", "notFound");
                returnValue = "redirect:/words/find";*/

                model.addAttribute("formLanguage", langService.findAllLanguages());
                returnValue = "words/wordregistration";

            } else if (results.size() == 1) {
                // 1 word found
                Word foundWord = results.iterator().next();
                returnValue = "redirect:/words/" + foundWord.getId();
            } else {
                // multiple words found found
                model.addAttribute("words", results);
                returnValue = "words/wordslist";
            }
        }
        return returnValue;
    }

    /**
     * This method will provide the medium to update an existing word.
     */
    @RequestMapping(value = {"/words/edit-word-{wordId}"}, method = RequestMethod.GET)
    public String editWord(@PathVariable Integer wordId, ModelMap model) {
        Word word = wordService.findById(wordId);
        model.addAttribute("formLanguage", langService.findAllLanguages());
        model.addAttribute("currentWord", word);
        model.addAttribute("edit", true);
        return "words/wordregistration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating word in database. It also validates the word input
     */
    @RequestMapping(value = {"/words/edit-word-{wordId}"}, method = RequestMethod.POST)
    public String updateWord(@Valid Word currentWord, BindingResult result, ModelMap model,
                             @PathVariable String wordId) {

        if (result.hasErrors()) {
            model.addAttribute("currentWord", currentWord);
            logger.info("\n ********** Validation error for word " + currentWord + "  *********\n");
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                logger.info(error.getObjectName().getClass() + " - " + error.getDefaultMessage());
            }
            return "words/wordregistration";
        }

        wordService.updateWord(currentWord);

        model.addAttribute("success", "Word " + currentWord.getContent() + " updated successfully");
        return "redirect:/words/" + currentWord.getId();
    }

    @RequestMapping(value = {"/words/delete-word-{wordId}"}, method = RequestMethod.GET)
    public String deleteWord(@PathVariable Integer wordId) {
        Word w = wordService.findById(wordId);
        wordService.deleteWord(w);

        return "redirect:/words/words-inlang-" + w.getLanguage().getId();

    }

}
