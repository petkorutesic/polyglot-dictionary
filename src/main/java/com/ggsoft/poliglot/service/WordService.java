package com.ggsoft.poliglot.service;

import java.util.Date;
import java.util.List;

import com.ggsoft.poliglot.dto.WordSearchDTO;
import com.ggsoft.poliglot.model.Word;
import com.ggsoft.poliglot.utils.ExternalWordLink;


public interface WordService {

	Word findById(int id);
	
	Word findByIdComplete(int id);
	
	void deleteWord(Word word);

	List<Word> findByWord(String word);
	
	List<Word> findAllWords();

	/* This method provides complex search on words depending wheteher a date, number of visits
	or just the free search method has been selected
	 */
	List<Word> findWordsGeneral(WordSearchDTO wordSearch);

	void updateWord(Word word);

	void saveWord(Word word);

	List<ExternalWordLink> findExternalLinks(Word word);

}
