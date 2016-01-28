package com.ggsoft.poliglot.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.ggsoft.poliglot.model.Word;



@Repository("wordDao")
public class WordDaoImpl extends AbstractDao<Integer, Word> implements WordDao {

	public Word findById(int id) {
		Word word = getByKey(id);
		if(word!=null){
		   //Hibernate.initialize(word.getWordMeanings());
		}
		return word;
	}
	
	public Word findByIdComplete(int id) {

		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id))
		.createAlias("wordMeanings", "meanings", JoinType.LEFT_OUTER_JOIN)
			.createAlias("wordMeanings.fromLinks", "links",JoinType.LEFT_OUTER_JOIN)
			.createAlias("wordMeanings.wordTypes", "wordtypes",JoinType.LEFT_OUTER_JOIN)
			.createAlias("wordMeanings.wordUsages", "wordusages",JoinType.LEFT_OUTER_JOIN);
//		crit.addOrder(Order.asc("content"));
		Word  word=  (Word) crit.list().iterator().next();
	
		return word;
	}

	public List<Word> findByWord(String word1) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.ilike("content", "%"+word1+"%"));
		crit.addOrder(Order.asc("content"));
		@SuppressWarnings("unchecked")
		List<Word>  words= (List<Word>) crit.list();
		return words;
	}

	@SuppressWarnings("unchecked")
	public List<Word> findAllWords() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("content"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Word>  words= (List<Word>) criteria.list();
		return words;
	}

	@SuppressWarnings("unchecked")
	public List<Word> findWordsLogsByDate(Date sooner) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("content"));
        criteria.createAlias("wordLogs", "logs", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.le("logs.timeVisit", sooner));
		criteria.add(Restrictions.eq("logs.active", 1));
		List<Word>  words= (List<Word>) criteria.list();
		return words;
	}

	@SuppressWarnings("unchecked")
	public List<Word> findWordsLogsByNumberOfVisits(int numOfVisits) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("word"));
		criteria.createAlias("wordLogs", "logs", JoinType.LEFT_OUTER_JOIN);
		ProjectionList pl = Projections.projectionList();
		pl.add(Projections.sqlGroupProjection("count( *) as visit_counter",
				"visit_counter HAVING visit_counter > "+numOfVisits, new String[]{"visit_counter"},
				new Type[]{StandardBasicTypes.INTEGER}));
		criteria.setProjection(pl);
		criteria.setResultTransformer(new AliasToBeanResultTransformer(
				Word.class));

		List<Word>  words= (List<Word>) criteria.list();
		return words;
	}

	public void save(Word word) {
		persist(word);
	}
	
	public void update(Word word) {
		saveOrUpdate(word);
	}
	
	public void deleteWord(Word word){
		delete(word);
	}

}
