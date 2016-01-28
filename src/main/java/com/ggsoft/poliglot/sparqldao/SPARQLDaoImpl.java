package com.ggsoft.poliglot.sparqldao;

import com.ggsoft.poliglot.dto.MeaningDto;
import com.ggsoft.poliglot.dto.WordDto;
import com.ggsoft.poliglot.dto.WordSPARQLSearchDTO;
import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.model.Word;
import org.apache.jena.query.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("sparqlDao")
public class SPARQLDaoImpl implements SPARQLDao {
    private static final Logger logger = Logger.getLogger(SPARQLDaoImpl.class);

    public WordDto findWordsWiktionary(WordSPARQLSearchDTO searchWord){
        String queryString = "\n"+
                "PREFIX terms: <http://wiktionary.dbpedia.org/terms/> \n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
                "PREFIX dc: <http://purl.org/dc/elements/1.1/> \n" +
                "PREFIX lemon: <http://www.monnet-project.eu/lemon#> \n" +
                "SELECT ?lexword ?meaning ?example \n" +

                " WHERE { \n" +
                " ?lexword rdfs:label \"" +searchWord.getContent() +"\"@de . \n" +
                " ?lexword lemon:sense ?sense . \n" +
                " ?sense terms:hasMeaning ?meaning. \n" +
                "  OPTIONAL {?sense terms:hasSentenceExample ?example.} \n}";

        logger.info("\n ****** Running SPRQL query ****** \n");

        logger.info("SPARQL Query:\n");
        logger.info(queryString + "\n");
        Query query = QueryFactory.create(queryString);
        String endpoint = "http://wiktionary.dbpedia.org/sparql";
        QueryExecution qexec = QueryExecutionFactory.sparqlService(endpoint, query);

        WordDto foundWord = new WordDto();
        foundWord.setContent(searchWord.getContent());
        foundWord.setLanguage(searchWord.getLanguage());

        try
        {
            ResultSet results = qexec.execSelect();
            while(results.hasNext()){
                QuerySolution soln = results.nextSolution();
                //Literal name = soln.getLiteral("x");
                MeaningDto newMeaning = new MeaningDto();
                newMeaning.setExplanation(soln.getLiteral("meaning").getString());
                //newMeaning.setLanguage(soln.getLiteral("meaning"));
                foundWord.addMeaning(newMeaning);
                logger.info(soln.getLiteral("meaning").getLanguage());
                logger.info(soln);
            }
        }
        finally{
            qexec.close();
        }
        return foundWord;
    }


}
