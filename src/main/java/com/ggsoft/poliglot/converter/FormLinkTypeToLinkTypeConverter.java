package com.ggsoft.poliglot.converter;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ggsoft.poliglot.model.LinkType;
import com.ggsoft.poliglot.service.LinkTypeService;

@Component
public class FormLinkTypeToLinkTypeConverter implements Converter<Object, LinkType> {
	
	public Logger logger = Logger.getLogger( FormLinkTypeToLinkTypeConverter.class); 
	@Autowired
	LinkTypeService linkTypeService;
	
	public LinkType convert(Object element){
		Integer id = Integer.parseInt((String)element);
		LinkType link = linkTypeService.findById(id);
		logger.info("LinkType in the form is converted " + link.toString());
		return link;
	}
}
