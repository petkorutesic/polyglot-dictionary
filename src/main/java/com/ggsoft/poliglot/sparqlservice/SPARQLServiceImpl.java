package com.ggsoft.poliglot.sparqlservice;

import com.ggsoft.poliglot.dto.WordDto;
import com.ggsoft.poliglot.dto.WordSPARQLSearchDTO;
import com.ggsoft.poliglot.model.Word;
import com.ggsoft.poliglot.sparqldao.SPARQLDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("sparqlService")
@Transactional
public class SPARQLServiceImpl implements SPARQLService {

	@Autowired
	SPARQLDao dao;

	public WordDto findWordsWiktionary(WordSPARQLSearchDTO searchWord){
		return dao.findWordsWiktionary(searchWord);
	}

}
