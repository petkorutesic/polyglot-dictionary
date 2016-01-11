package com.ggsoft.poliglot.converter;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.service.LanguageService;

@Component
public class FormLanguageToLanguageConverter implements Converter<Object, Language> {
	
	public Logger logger = Logger.getLogger( FormLanguageToLanguageConverter.class); 
	@Autowired
	LanguageService langService;
	
	public Language convert(Object element){
		Integer id = Integer.parseInt((String)element);
		Language lang = langService.findById(id);
		logger.info("Langauge is converted" + lang.toString());
		return lang;
	}
}
