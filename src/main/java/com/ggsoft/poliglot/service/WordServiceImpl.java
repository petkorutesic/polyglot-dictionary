package com.ggsoft.poliglot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.ggsoft.poliglot.dto.WordSearchDTO;
import com.ggsoft.poliglot.model.SearchType;
import com.ggsoft.poliglot.utils.ExternalWordLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggsoft.poliglot.dao.WordDao;
import com.ggsoft.poliglot.model.Word;

@Service("wordService")
@Transactional
public class WordServiceImpl implements WordService{

    @Autowired
    WordDao dao;

    public Word findById(int id) {
        return dao.findById(id);
    }

    public List<Word> findByWord(String type){
        return dao.findByWord(type, SearchType.SIMPLE);
    }

    public List<Word> findAllWords() {
        return dao.findAllWords();
    }

    public List<Word> findWordsGeneral(WordSearchDTO wordSearch) {
        List<Word> words = null;
        switch(wordSearch.getSearchMode()){
            case SIMPLE:
                words = dao.findByWord(wordSearch.getContent(), SearchType.SIMPLE);
                break;
            case DATE:
                words = dao.findWordsLogsByDate(wordSearch.getContent(), wordSearch.getFromDate(),
                        wordSearch.getUntilDate(), wordSearch.getLanguages(), SearchType.TIME_VISIT_A); break;
            case NUM_VISIT:
                words = dao.findWordsLogsByNumberOfVisits(wordSearch.getContent(),wordSearch.getNumberOfVisits());
                break;
            case NUM_VISIT_RANDOM:
                words = dao.findWordsLogsByNumberOfVisits(wordSearch.getContent(),wordSearch.getNumberOfVisits());
                Collections.shuffle(words);
                break;
            case LAST_VISIT_ASCENDING_TIME:
                words = dao.findWordsLogsByDate(wordSearch.getContent(), wordSearch.getFromDate(),
                    wordSearch.getUntilDate(), wordSearch.getLanguages(), SearchType.TIME_VISIT_A);
                break;
            case LAST_VISIT_DESCENDING_TIME:
                words = dao.findWordsLogsByDate(wordSearch.getContent(), wordSearch.getFromDate(),
                    wordSearch.getUntilDate(), wordSearch.getLanguages(), SearchType.TIME_VISIT_A);
                break;
            case TIME_CREATION_ASCENDING:
                words = dao.findByWord(wordSearch.getContent(), SearchType.TIME_CREATE_A);
                break;
            case TIME_CREATION_DESCENDING: words = dao.findByWord(wordSearch.getContent(), SearchType.TIME_CREATE_D);
                break;
            default:
                words = dao.findByWord(wordSearch.getContent(), SearchType.SIMPLE);
                break;
        }

        return words;
    }

    public void deleteWord(Word word){
        dao.deleteWord(word);
    }

    public void saveWord(Word word){
        dao.save(word);
    }

    public void updateWord(Word word) { dao.update(word); }

    public Word findByIdComplete(int id) {
        // TODO Auto-generated method stub
        return dao.findByIdComplete(id);
    }
    /*
        TODO: This should be implemented differently
     */
    public List<ExternalWordLink> findExternalLinks(Word word) {
        List<ExternalWordLink> externalLinks= new ArrayList<ExternalWordLink>();
        switch (word.getLanguage().getLang()) {
            case "Deutsch":
            {
                ExternalWordLink wiki = new ExternalWordLink();
                wiki.setLinkName("Wiktionary");
                wiki.setLinkAddress("https://de.wiktionary.org/wiki/"+word.getContent());
                externalLinks.add(wiki);
                ExternalWordLink google = new ExternalWordLink();
                google.setLinkName("Google");
                google.setLinkAddress("https://translate.google.com/#de/en/"+word.getContent());
                externalLinks.add(google);
                ExternalWordLink dict = new ExternalWordLink();
                dict.setLinkName("dict.cc");
                dict.setLinkAddress("http://www.dict.cc/?s="+word.getContent());
                externalLinks.add(dict);
                ExternalWordLink freedict = new ExternalWordLink();
                freedict.setLinkName("freeDict");
                freedict.setLinkAddress("http://de.thefreedictionary.com/"+word.getContent());
                externalLinks.add(freedict);
            }
                break;
            case "Italiano":
            {
                ExternalWordLink wiki = new ExternalWordLink();
                wiki.setLinkName("Wiktionary");
                wiki.setLinkAddress("https://it.wiktionary.org/wiki/"+word.getContent());
                externalLinks.add(wiki);
                ExternalWordLink google = new ExternalWordLink();
                google.setLinkName("Google");
                google.setLinkAddress("https://translate.google.com/#it/en/"+word.getContent());
                externalLinks.add(google);
                ExternalWordLink dict = new ExternalWordLink();
                dict.setLinkName("dict.cc");
                dict.setLinkAddress("http://www.iten.dict.cc/?s="+word.getContent());
                externalLinks.add(dict);
            }
            case "English":
            {
                ExternalWordLink wiki = new ExternalWordLink();
                wiki.setLinkName("Wiktionary");
                wiki.setLinkAddress("https://wiktionary.org/wiki/"+word.getContent());
                externalLinks.add(wiki);
                ExternalWordLink google = new ExternalWordLink();
                google.setLinkName("Google");
                google.setLinkAddress("https://translate.google.com/#en/en/"+word.getContent());
                externalLinks.add(google);
                ExternalWordLink dict = new ExternalWordLink();
                dict.setLinkName("Dictionary");
                dict.setLinkAddress("http://www.dictionary.com/browse/"+word.getContent());
                externalLinks.add(dict);
                ExternalWordLink theFreeDict = new ExternalWordLink();
                theFreeDict.setLinkName("TheFreeDict");
                theFreeDict.setLinkAddress("http://www.thefreedictionary.com/"+word.getContent());
                externalLinks.add(theFreeDict);
            }
            break;
            default:
                break;

        }
        return externalLinks;
    }
}
