package com.ggsoft.poliglot.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import com.ggsoft.poliglot.model.LinkType;


@Repository("linkTypeDao")
public class LinkTypeDaoImpl extends AbstractDao<Integer, LinkType> implements LinkTypeDao {

	public LinkType findById(int id) {
		LinkType type = getByKey(id);
		if(type!=null){
//			Hibernate.initialize(type.getSynonymsTo());
		}
		return type;
	}

	@SuppressWarnings("unchecked")
	public List<LinkType> findAllLinkTypes() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<LinkType>  links= (List<LinkType>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(Link user : users){
			Hibernate.initialize(user.getLinkProfiles());
		}*/
		return links;
	}

	public void delete(LinkType linkType){
		delete(linkType);
	}
	
	public void save(LinkType type) {
	    persist(type);	
	}

}
