package com.ggsoft.poliglot.service;

import java.util.List;

import com.ggsoft.poliglot.model.Word;


public interface WordService {

	Word findById(int id);
	
	Word findByIdComplete(int id);
	
	void deleteWord(Word word);

	List<Word> findByWord(String word);
	
	List<Word> findAllWords();

	void updateWord(Word word);

	void saveWord(Word word);
	
}
