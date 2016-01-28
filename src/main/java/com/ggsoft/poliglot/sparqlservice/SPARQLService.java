package com.ggsoft.poliglot.sparqlservice;

import com.ggsoft.poliglot.dto.WordDto;
import com.ggsoft.poliglot.dto.WordSPARQLSearchDTO;

public interface SPARQLService {
	WordDto findWordsWiktionary(WordSPARQLSearchDTO searchWord);
}
