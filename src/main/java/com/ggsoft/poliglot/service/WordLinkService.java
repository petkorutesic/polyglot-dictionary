package com.ggsoft.poliglot.service;

import java.util.List;

import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.model.WordLink;


public interface WordLinkService {

	WordLink findById(int id);
	
	void deleteWordLink(WordLink word);

	List<WordLink> findWordLinksOfMeaning(Meaning meaning);
	
	void updateWordLink(WordLink wordLink);

	void saveWordLink(WordLink wordLink);
	
}
