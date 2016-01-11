package com.ggsoft.poliglot.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ggsoft.poliglot.dao.AbstractDao;
import com.ggsoft.poliglot.model.UsageType;





@Repository("usageTypeDao")
public class UsageTypeDaoImpl extends AbstractDao<Integer, UsageType> implements UsageTypeDao {

	public UsageType findById(int id) {
		UsageType type = getByKey(id);

		return type;
	}

	@SuppressWarnings("unchecked")
	public List<UsageType> findAllUsageTypes() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("text"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<UsageType>  wordUsageTypes= (List<UsageType>) criteria.list();
		return wordUsageTypes;
	}

	public UsageType findByText(String text) {
        System.out.println("Type: "+ text);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("text", text));
		UsageType type = (UsageType)crit.uniqueResult();
		if(type!=null){
			// Hibernate.initialize(type.getSynonymsFrom());
		}
		return type;

	}

	public void save(UsageType wordUsageType) {
	    persist(wordUsageType);	
	}
	
	public void delete(UsageType wordUsageType){
		delete(wordUsageType);
	}


}
