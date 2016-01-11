package com.ggsoft.poliglot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggsoft.poliglot.dao.WordTypeDao;
import com.ggsoft.poliglot.model.WordType;



@Service("wordTypeService")
@Transactional
public class WordTypeServiceImpl implements WordTypeService{
	
	@Autowired
	WordTypeDao dao;
	
	public WordType findById(int id) {
		return dao.findById(id);
	}

	public List<WordType> findAllWordTypes() {
		return dao.findAllWordTypes();
	}

	public void deleteWordType(WordType wordType){
		dao.delete(wordType);
	}

	public void saveWordType(WordType wordType){
		dao.save(wordType);
	}

	public void updateWordType(WordType WordType) {
		dao.save(WordType);
		
	}


}
