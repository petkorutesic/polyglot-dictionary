package com.ggsoft.poliglot.dao;

import java.util.List;

import com.ggsoft.poliglot.model.LinkType;


public interface LinkTypeDao {

	LinkType findById(int id);
	
	void save(LinkType linkType);
	
	List<LinkType> findAllLinkTypes();
	
	void delete(LinkType linkType);
}

