package com.ggsoft.poliglot.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.model.Meaning;

@Repository("langDao")
public class LanguageDaoImpl extends AbstractDao<Integer, Language>implements LanguageDao {

	public Language findById(int id) {
		Language lang = getByKey(id);
		if (lang != null) {
			Hibernate.initialize(lang.getLanguageWords());
		}
		return lang;
	}

	public void save(Language lang) {
		persist(lang);
	}

	@SuppressWarnings("unchecked")
	public List<Language> findAllLanguages() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("lang"));
		List<Language>  languages= (List<Language>) criteria.list();
		return languages;
	}
	
	public void addMeaning(Language l, Meaning m){
		Hibernate.initialize(l.getLanguageWords());
		l.addMeaning(m);
	}

}
