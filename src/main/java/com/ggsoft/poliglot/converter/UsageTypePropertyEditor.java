package com.ggsoft.poliglot.converter;

import java.beans.PropertyEditorSupport;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ggsoft.poliglot.model.UsageType;
import com.ggsoft.poliglot.service.UsageTypeService;

@Component
public class UsageTypePropertyEditor extends PropertyEditorSupport {

	public static final Logger logger = Logger.getLogger(UsageTypePropertyEditor.class);
	@Autowired
	UsageTypeService usageTypeService;

	@Override

	public void setAsText(String element) {

		logger.info("\n ********** Trying to convert usageType. " + element + "  *********\n");
		try {
			Integer id = Integer.parseInt((String) element);
			logger.info("Number is 8888888 " + id);
			UsageType usageType  = usageTypeService.findById(id);
			logger.info("UsageType is converted ");
			setValue(usageType);
		} catch (NullPointerException ex) {
			logger.log(Level.ERROR, "usageType is not converted ", ex);
		}  catch (Exception ex) {
			logger.log(Level.ERROR, "usageType is not converted ", ex);
		}
	}

}
