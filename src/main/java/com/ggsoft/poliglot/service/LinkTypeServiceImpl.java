package com.ggsoft.poliglot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggsoft.poliglot.dao.LinkTypeDao;
import com.ggsoft.poliglot.model.LinkType;


@Service("linkTypeService")
@Transactional
public class LinkTypeServiceImpl implements LinkTypeService{
	
	@Autowired
	LinkTypeDao dao;
	
	public LinkType findById(int id) {
		return dao.findById(id);
	}

	public List<LinkType> findAllLinkTypes() {
		return dao.findAllLinkTypes();
	}

	public void deleteLinkType(LinkType linkType){
		dao.delete(linkType);
	}

	public void saveLinkType(LinkType linkType){
		dao.save(linkType);
	}

	public void updateLinkType(LinkType linkType) {
		dao.save(linkType);
		
	}


}
