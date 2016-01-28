package com.ggsoft.poliglot.service;

import java.util.Date;
import java.util.List;

import com.ggsoft.poliglot.dto.WordSearchDTO;
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
        return dao.findByWord(type);
    }

    public List<Word> findAllWords() {
        return dao.findAllWords();
    }

    public List<Word> findWordsGeneral(WordSearchDTO wordSearch) {
        List<Word> words = null;
        switch(wordSearch.getSearchMode()){
            case "F" : words = dao.findByWord(wordSearch.getContent());  break;
            case "D" : words = dao.findWordsLogsByDate(wordSearch.getFromDate()); break;
            case "N" : words = dao.findWordsLogsByNumberOfVisits(wordSearch.getNumberOfVisits()); break;
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


}
