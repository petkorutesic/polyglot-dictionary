package com.ggsoft.poliglot.dao;

import java.util.List;

import com.ggsoft.poliglot.model.Word;


public interface WordDao {

	Word findById(int id);
	
	Word findByIdComplete(int id);
	
	List<Word> findByWord(String word);
	
	void save(Word word);
	
	void update(Word word);
	
	List<Word> findAllWords();
	
	void deleteWord(Word word);
}

