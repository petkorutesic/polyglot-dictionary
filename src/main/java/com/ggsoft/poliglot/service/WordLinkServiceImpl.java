package com.ggsoft.poliglot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggsoft.poliglot.dao.WordLinkDao;
import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.model.WordLink;


@Service("wordLinkService")
@Transactional
public class WordLinkServiceImpl implements WordLinkService{
	
	@Autowired
	WordLinkDao dao;
	
	public WordLink findById(int id) {
		return dao.findById(id);
	}

	public List<WordLink> findWordLinksOfMeaning(Meaning meaning) {
		return dao.findAllWordLinksOfMeaning(meaning);
	}

	public void deleteWordLink(WordLink wordLink){
		dao.delete(wordLink);
	}

	public void saveWordLink(WordLink wordLink){
		dao.save(wordLink);
	}

	public void updateWordLink(WordLink wordLink) {
		dao.save(wordLink);
		
	}


}
