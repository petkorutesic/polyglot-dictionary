package com.ggsoft.poliglot.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ggsoft.poliglot.dao.AbstractDao;
import com.ggsoft.poliglot.model.WordType;




@Repository("wordTypeDao")
public class WordTypeDaoImpl extends AbstractDao<Integer, WordType> implements WordTypeDao {

	public WordType findById(int id) {
		WordType type = getByKey(id);

		return type;
	}

	@SuppressWarnings("unchecked")
	public List<WordType> findAllWordTypes() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("text"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<WordType>  wordtypes= (List<WordType>) criteria.list();
		return wordtypes;
	}

	public WordType findByText(String text) {
        System.out.println("Type: "+ text);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("text", text));
		WordType type = (WordType)crit.uniqueResult();
		if(type!=null){
			// Hibernate.initialize(type.getSynonymsFrom());
		}
		return type;

	}

	public void save(WordType type) {
	    persist(type);	
	}
	
	public void delete(WordType type){
		delete(type);
	}


}
