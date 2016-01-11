package com.ggsoft.poliglot.converter;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.service.MeaningService;

@Component
public class FormMeaningToMeaningConverter implements Converter<Object, Meaning> {
	
	public static final Logger logger = Logger.getLogger( FormMeaningToMeaningConverter.class); 
	@Autowired
	MeaningService meaningService;
	
	public Meaning convert(Object element){
		logger.info("\n ********** Trying to convert meaning. " + element + "  *********\n");
		
		if (element ==null)
        throw new ConversionFailedException(TypeDescriptor.valueOf(Meaning.class),
					TypeDescriptor.valueOf(String.class),element , null);
		Integer id = Integer.parseInt((String)element);
		Meaning meaning = meaningService.findByIdSimple(id);
		logger.info("Meaning in the form is converted " );
		return meaning;
	}
}
