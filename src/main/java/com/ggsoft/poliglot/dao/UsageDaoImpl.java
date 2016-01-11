package com.ggsoft.poliglot.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ggsoft.poliglot.dao.AbstractDao;
import com.ggsoft.poliglot.model.Usage;





@Repository("usageDao")
public class UsageDaoImpl extends AbstractDao<Integer, Usage> implements UsageDao {

	public Usage findById(int id) {
		Usage type = getByKey(id);

		return type;
	}

	@SuppressWarnings("unchecked")
	public List<Usage> findAllUsages() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("text"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Usage>  wordUsages= (List<Usage>) criteria.list();
		return wordUsages;
	}

	public Usage findByText(String text) {
        System.out.println("Type: "+ text);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("text", text));
		Usage type = (Usage)crit.uniqueResult();
		if(type!=null){
			// Hibernate.initialize(type.getSynonymsFrom());
		}
		return type;

	}

	public void save(Usage wordUsage) {
	    persist(wordUsage);	
	}
	
	public void delete(Usage wordUsage){
		delete(wordUsage);
	}


}
