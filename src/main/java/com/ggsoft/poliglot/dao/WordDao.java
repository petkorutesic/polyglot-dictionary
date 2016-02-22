package com.ggsoft.poliglot.dao;

import com.ggsoft.poliglot.model.Word;
import org.joda.time.DateTime;

import java.util.List;


public interface WordDao {

	Word findById(int id);
	
	Word findByIdComplete(int id);
	
	List<Word> findByWord(String word);

	List<Word> findWordsLogsByNumberOfVisits(String word, int numOfVisits);

	List<Word> findWordsLogsByDate(String word, DateTime from, DateTime till);

	void save(Word word);
	
	void update(Word word);
	
	List<Word> findAllWords();
	
	void deleteWord(Word word);
}

