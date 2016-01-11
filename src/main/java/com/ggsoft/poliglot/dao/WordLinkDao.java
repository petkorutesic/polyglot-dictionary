package com.ggsoft.poliglot.dao;

import java.util.List;

import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.model.WordLink;


public interface WordLinkDao {

	WordLink findById(int id);
	
	void save(WordLink wordLink);
	
	List<WordLink> findAllWordLinksOfMeaning(Meaning meaning);
	
	void delete(WordLink wordLink);
}

