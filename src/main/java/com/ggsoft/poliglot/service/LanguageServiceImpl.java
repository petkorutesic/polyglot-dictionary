package com.ggsoft.poliglot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggsoft.poliglot.dao.LanguageDao;
import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.model.Meaning;


@Service("languageService")
@Transactional
public class LanguageServiceImpl implements LanguageService{
	
	@Autowired
	LanguageDao dao;
	
	public Language findById(int id) {
		return dao.findById(id);
	}

	public List<Language> findAllLanguages() {
		return dao.findAllLanguages();
	}
	
	public void addMeaning(Language l, Meaning m){
	}

	public void saveLanguage(Language lang){
		dao.save(lang);
	}


}
