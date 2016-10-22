package com.ggsoft.poliglot.dao;

import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.model.SearchType;
import com.ggsoft.poliglot.model.Word;
import org.joda.time.DateTime;

import java.util.List;
import java.util.Set;


public interface WordDao {

	Word findById(int id);
	
	Word findByIdComplete(int id);
	
	List<Word> findByWord(String word, SearchType searchType);

	List<Word> findWordsLogsByNumberOfVisits(String word, int numOfVisits);

	List<Word> findWordsLogsByDate(String word, DateTime from, DateTime till, Set<Language> languages, SearchType type);

	void save(Word word);
	
	void update(Word word);
	
	List<Word> findAllWords();
	
	void deleteWord(Word word);
}

