package com.ggsoft.poliglot.sparqldao;


import com.ggsoft.poliglot.dto.WordDto;
import com.ggsoft.poliglot.dto.WordSPARQLSearchDTO;

public interface SPARQLDao {
    WordDto findWordsWiktionary(WordSPARQLSearchDTO searchWord);

}
