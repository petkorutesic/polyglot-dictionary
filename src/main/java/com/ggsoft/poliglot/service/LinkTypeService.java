package com.ggsoft.poliglot.service;

import java.util.List;

import com.ggsoft.poliglot.model.LinkType;


public interface LinkTypeService {

	LinkType findById(int id);
	
	void deleteLinkType(LinkType link);

	List<LinkType> findAllLinkTypes();

	void updateLinkType(LinkType linktype);

	void saveLinkType(LinkType linkType);
	
}
