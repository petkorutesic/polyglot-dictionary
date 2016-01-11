package com.ggsoft.poliglot.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.model.Word;


@Repository("meaningDao")
public class MeaningDaoImpl extends AbstractDao<Integer, Meaning> implements MeaningDao {
	
	public Meaning findByIdSimple(int id) {
		Meaning meaning = getByKey(id);
		return meaning;
	}
	
	public Meaning findById(int id) {
		//compleate reading of meaning
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id))
			.createAlias("fromLinks", "links",JoinType.LEFT_OUTER_JOIN)
			.createAlias("wordTypes", "wordtypes",JoinType.LEFT_OUTER_JOIN)
		    .createAlias("wordUsages", "wordusages",JoinType.LEFT_OUTER_JOIN);

//		crit.addOrder(Order.asc("content"));
		Meaning  meaning =  (Meaning) crit.list().iterator().next();
	
		return meaning;
	}

	public Meaning findByMeaning(String meaning1) {
		System.out.println("Meaning: "+ meaning1);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("meaning", meaning1));
		Meaning meaning = (Meaning)crit.uniqueResult();
		if(meaning!=null){
	//		Hibernate.initialize(meaning.getSynonymsFrom());
		}
		return meaning;
	}

	@SuppressWarnings("unchecked")
	public List<Meaning> findAllMeanings() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("explanation"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Meaning>  meanings= (List<Meaning>) criteria.list();
		
//		for(Meaning mean : meanings){
//			Hibernate.initialize(mean.getToLinks());
//		}
		return meanings;
	}
	
	@SuppressWarnings("unchecked")
	public List<Meaning> findMeaningsForWord(Word w) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("explanation"));
		criteria.add(Restrictions.eq("word", w));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Meaning>  meanings= (List<Meaning>) criteria.list();
		
		return meanings;
	}

	public void save(Meaning meaning) {
		persist(meaning);
	}
	
	public void update(Meaning meaning) {
		saveOrUpdate(meaning);
	}
	
	public void deleteMeaning(Meaning meaning) {
		delete(meaning);
	}
}
