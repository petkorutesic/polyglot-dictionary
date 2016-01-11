package com.ggsoft.poliglot.converter;

import java.beans.PropertyEditorSupport;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.service.MeaningService;

@Component
public class MeaningPropertyEditor extends PropertyEditorSupport {

	public static final Logger logger = Logger.getLogger(MeaningPropertyEditor.class);
	@Autowired
	MeaningService meaningService;

	@Override

	public void setAsText(String element) {

		logger.info("\n ********** Trying to convert meaning. " + element + "  *********\n");
		try {
			Integer id = Integer.parseInt((String) element);
			logger.info("Number is 8888888 " + id);
			Meaning meaning  = meaningService.findById(id);
			logger.info("Meaning in the form is converted ");
			setValue(meaning);
		} catch (NullPointerException ex) {
			logger.log(Level.ERROR, "Meaning is not converted ", ex);
		}  catch (Exception ex) {
			logger.log(Level.ERROR, "Meaning is not converted ", ex);
		}
	}

}
