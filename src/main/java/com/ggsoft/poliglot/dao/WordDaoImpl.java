package com.ggsoft.poliglot.dao;

import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.model.Word;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
	public List<Word> findWordsLogsByDate(String word, DateTime from, DateTime till, Set<Language> languages) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("content"));
		criteria.add(Restrictions.ilike("content", "%"+word+"%"));
        criteria.createAlias("wordLogs", "logs", JoinType.LEFT_OUTER_JOIN);
		if (from != null)
			criteria.add(Restrictions.ge("logs.timeVisit", from));
		if (till != null)
			criteria.add(Restrictions.le("logs.timeVisit", till));
		//Extracting only keys because comparison can be carried out only on that level
		if (languages != null && !languages.isEmpty())
			criteria.add(Restrictions.in("language", languages));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Word>  words= (List<Word>) criteria.list();
		return words;
	}

	@SuppressWarnings("unchecked")
	public List<Word> findWordsLogsByNumberOfVisits(String word, int numOfVisits) {

        String hql = "from Word w \n" +
                    " where w.id in (select w1.id " +
                                  " from Word as w1 inner join w1.wordLogs as l \n" +
                                    " where w1.id = w.id " +
                                    "group by w1.id having count(l.id) > :numOfVisits ) \n" +
				     "and w.content like :word order w.content";

        //Criteria doesnt work for complex queries
        Query query= getSession().createQuery(hql);
        query.setParameter("numOfVisits", (long) numOfVisits);
        query.setParameter("word", "%" + word + "%");
		List<Word>  words= query.list();
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
