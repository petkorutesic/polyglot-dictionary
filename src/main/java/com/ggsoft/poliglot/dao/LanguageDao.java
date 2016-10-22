package com.ggsoft.poliglot.dao;

import java.util.List;

import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.model.Meaning;

public interface LanguageDao {

	Language findById(int id);

	Language findByName(String langName);

	void save(Language lang);

	List<Language> findAllLanguages();

	void addMeaning(Language l, Meaning m);

	Language loadFullLanguageById(int id);

}
