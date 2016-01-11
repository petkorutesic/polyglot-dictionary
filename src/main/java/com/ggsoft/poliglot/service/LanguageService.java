package com.ggsoft.poliglot.service;

import java.util.List;

import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.model.Meaning;


public interface LanguageService {

	Language findById(int id);
	
	List<Language> findAllLanguages();

	void saveLanguage(Language lang);
	
	void addMeaning(Language l, Meaning m);
	
}
