package com.ggsoft.poliglot.converter;

/**
 * Created by info on 20.3.16..
 */

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

import com.ggsoft.poliglot.model.WordType;
import com.ggsoft.poliglot.service.WordTypeService;

@Component
public class FormLanguageToLanguageEditor extends PropertyEditorSupport {

    public static final Logger logger = Logger.getLogger(FormLanguageToLanguageEditor.class);
    @Autowired
    WordTypeService wordTypeService;

    @Override

    public void setAsText(String element) {

        logger.info("\n ********** Trying to convert Language. " + element + "  *********\n");
        try {
            Integer id = Integer.parseInt((String) element);
            WordType wordType  = wordTypeService.findById(id);
            setValue(wordType);
        } catch (NullPointerException ex) {
            logger.log(Level.ERROR, "Language is not converted ", ex);
        }  catch (Exception ex) {
            logger.log(Level.ERROR, "Language is not converted ", ex);
        }
    }

}
