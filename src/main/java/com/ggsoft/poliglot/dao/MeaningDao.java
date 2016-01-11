package com.ggsoft.poliglot.dao;

import java.util.List;

import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.model.Word;


public interface MeaningDao {

	Meaning findById(int id);
	
	Meaning findByIdSimple(int id);
	
	Meaning findByMeaning(String word);
	
	void save(Meaning meaning);
	
	void update(Meaning meanig);
	
	List<Meaning> findAllMeanings();
	
	List<Meaning> findMeaningsForWord(Word w);
	
	void deleteMeaning(Meaning word);
}

