package com.ggsoft.poliglot.sparqlservice;

import com.ggsoft.poliglot.dto.MeaningDto;
import com.ggsoft.poliglot.dto.UsageDto;
import com.ggsoft.poliglot.dto.WordDto;
import com.ggsoft.poliglot.dto.WordSPARQLSearchDTO;
import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.model.Usage;
import com.ggsoft.poliglot.model.Word;
import com.ggsoft.poliglot.service.MeaningService;
import com.ggsoft.poliglot.service.UsageService;
import com.ggsoft.poliglot.service.WordService;
import com.ggsoft.poliglot.sparqldao.SPARQLDao;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("sparqlService")
@Transactional
public class SPARQLServiceImpl implements SPARQLService {

    private static final Logger logger =Logger.getLogger(SPARQLServiceImpl.class);;

	@Autowired
	SPARQLDao dao;

    @Autowired
    WordService wordService;

    @Autowired
    MeaningService meaningService;

    @Autowired
    UsageService usageService;

	public WordDto findWordsWiktionary(WordSPARQLSearchDTO searchWord){
		return dao.findWordsWiktionary(searchWord);
	}

	/*
		This service contains functionality for saving wordDto object
		complely with saving meanings and their usages
		Out of DTOs we have to create DAOs  and carry out necessary operations

		I should have used Dozer mapper for this stuff but now it's an abuse of the Service layer
	 */
	public Integer saveWord(WordDto wordDto){

        Word word = new Word();
        word.setContent(wordDto.getContent());
        word.setTimeCreation(new DateTime());
        word.setLanguage(wordDto.getLanguage());
        wordService.saveWord(word);

        for (MeaningDto meaningDao : wordDto.getWordMeanings()) {
            Meaning newMeaning = new Meaning();
            newMeaning.setExplanation(meaningDao.getExplanation());
            newMeaning.setWord(word);
            newMeaning.setLanguage(meaningDao.getLanguage());
            meaningService.saveMeaning(newMeaning);
            logger.info("Meaning from meaningDTO   " + newMeaning.toString());
            for (UsageDto usage : meaningDao.getWordUsages()) {

                Usage newUsage = new Usage();
                newUsage.setText(usage.getText());
                usageService.saveUsage(newUsage);

                //Adding new usage example to the meaning list
                newMeaning.getWordUsages().add(newUsage);
                meaningService.updateMeaning(newMeaning);
            }
        }

        return word.getId();

	}



}
