package com.ggsoft.poliglot.dao;

import com.ggsoft.poliglot.model.LogWord;
import com.ggsoft.poliglot.model.Word;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("logWordDao")
public class LogWordDaoImpl extends AbstractDao<Integer, LogWord> implements LogWordDao {
	private static final Logger logger = Logger.getLogger(LogWordDaoImpl.class);

	public LogWord findById(int id) {
		LogWord log = getByKey(id);
		return log;
	}


	public List<LogWord> findLogsForWord(Word w) {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("timeVisit"));
        criteria.add(Restrictions.eq("word", w));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<LogWord>  logWords= (List<LogWord>) criteria.list();
		return logWords;
	}

	public void deleteLogsForWord(Word w){
		String hql = "delete from LogWord where word= :wordId";
		Query query= getSession().createQuery(hql);
		query.setParameter("wordId", w).executeUpdate();
	}

	public void saveLogWord(LogWord logWord) {
	    persist(logWord);
	}
	
	public void deleteLogWord(LogWord logWord){
		delete(logWord);
	}


}
