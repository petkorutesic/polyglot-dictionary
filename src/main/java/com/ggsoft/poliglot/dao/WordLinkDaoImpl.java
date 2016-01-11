package com.ggsoft.poliglot.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.model.WordLink;




@Repository("wordLinkDao")
public class WordLinkDaoImpl extends AbstractDao<Integer, WordLink> implements WordLinkDao {

	public WordLink findById(int id) {
		WordLink type = getByKey(id);
		if(type!=null){
//			Hibernate.initialize(type.getSynonymsTo());
		}
		return type;
	}

	@SuppressWarnings("unchecked")
	public List<WordLink> findAllWordLinksOfMeaning(Meaning meaning) {
		Criteria criteria = createEntityCriteria().add(Restrictions.eq("meaningFrom",meaning));
		List<WordLink>  wordsLinks = (List<WordLink>) criteria.list();
		return wordsLinks;
	}

	public void delete(WordLink wordLink){
		delete(wordLink);
	}

	
	public void save(WordLink type) {
	    persist(type);	
	}

}
