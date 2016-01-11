package com.ggsoft.poliglot.service;

import java.util.List;

import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.model.Word;


public interface MeaningService {

	Meaning findById(int id);
	
	Meaning findByIdSimple(int id);
	
	Meaning findByMeaning(String meaning);
	
	List<Meaning> findAllMeanings();
	
	List<Meaning> findMeaningsForWord( Word w);

	void updateMeaning(Meaning meaning);

	void saveMeaning(Meaning meaning);
	
	void deleteMeaning(Meaning meaning);
}
