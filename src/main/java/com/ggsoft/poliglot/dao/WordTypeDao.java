package com.ggsoft.poliglot.dao;

import java.util.List;

import com.ggsoft.poliglot.model.WordType;


public interface WordTypeDao {

	WordType findById(int id);
	
	WordType findByText(String text);
	
	List<WordType> findAllWordTypes();
	
	void save(WordType wordtype);
	
	void delete(WordType wordtype);
	
	
}

