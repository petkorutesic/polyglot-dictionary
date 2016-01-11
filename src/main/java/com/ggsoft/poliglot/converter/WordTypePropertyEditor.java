package com.ggsoft.poliglot.converter;

import java.beans.PropertyEditorSupport;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ggsoft.poliglot.model.WordType;
import com.ggsoft.poliglot.service.WordTypeService;

@Component
public class WordTypePropertyEditor extends PropertyEditorSupport {

	public static final Logger logger = Logger.getLogger(WordTypePropertyEditor.class);
	@Autowired
	WordTypeService wordTypeService;

	@Override

	public void setAsText(String element) {

		logger.info("\n ********** Trying to convert wordType. " + element + "  *********\n");
		try {
			Integer id = Integer.parseInt((String) element);
			logger.info("Number is 8888888 " + id);
			WordType wordType  = wordTypeService.findById(id);
			logger.info("WordType is converted ");
			setValue(wordType);
		} catch (NullPointerException ex) {
			logger.log(Level.ERROR, "WordType is not converted ", ex);
		}  catch (Exception ex) {
			logger.log(Level.ERROR, "WordType is not converted ", ex);
		}
	}

}
