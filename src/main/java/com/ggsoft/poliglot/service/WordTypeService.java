package com.ggsoft.poliglot.service;

import java.util.List;

import com.ggsoft.poliglot.model.WordType;


public interface WordTypeService {

	WordType findById(int id);
	
	void deleteWordType(WordType type);

	List<WordType> findAllWordTypes();

	void updateWordType(WordType wordtype);

	void saveWordType(WordType wordType);
	
}
