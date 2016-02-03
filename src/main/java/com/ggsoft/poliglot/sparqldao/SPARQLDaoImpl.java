package com.ggsoft.poliglot.sparqldao;

import com.ggsoft.poliglot.dto.MeaningDto;
import com.ggsoft.poliglot.dto.UsageDto;
import com.ggsoft.poliglot.dto.WordDto;
import com.ggsoft.poliglot.dto.WordSPARQLSearchDTO;
import com.ggsoft.poliglot.service.LanguageService;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("sparqlDao")
public class SPARQLDaoImpl implements SPARQLDao {

    @Autowired
    LanguageService langService;

    private static final Logger logger = Logger.getLogger(SPARQLDaoImpl.class);

    public WordDto findWordsWiktionary(WordSPARQLSearchDTO searchWord){
        String queryString = "\n"+
                "PREFIX terms: <http://wiktionary.dbpedia.org/terms/> \n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n" +
                "PREFIX dc: <http://purl.org/dc/elements/1.1/> \n" +
                "PREFIX lemon: <http://www.monnet-project.eu/lemon#> \n" +
                "SELECT ?lexword ?meaning ?example \n" +

                " WHERE { \n" +
                " ?lexword rdfs:label \"" +searchWord.getContent() +"\"@en . \n" +
                " {?lexword lemon:sense ?sense . } UNION { ?lexword terms:hasSense ?sense . } \n" +
                " ?sense terms:hasMeaning ?meaning. \n" +
                "  OPTIONAL{{?sense terms:hasExampleSentence ?example.} UNION  {?sense terms:hasExample ?example.}} \n}";

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
                Literal meaningLiteral = soln.getLiteral("meaning");
                MeaningDto newMeaning = new MeaningDto();
                newMeaning.setExplanation(meaningLiteral.getString());
                newMeaning.setLanguage(langService.
                        findByName(decodeLanguage(meaningLiteral.getLanguage())));

                if(soln.getLiteral("example")!= null){
                    UsageDto newUsage = new UsageDto(soln.getLiteral("example").toString());
                    newMeaning.addUsage(newUsage);
                }
                //newMeaning.setLanguage(soln.getLiteral("meaning"));
                foundWord.addMeaning(newMeaning);
                logger.info(soln.getLiteral("meaning").getLanguage());
                logger.info(soln);
            }
        } finally{
            qexec.close();
        }
        return foundWord;
    }

    private String decodeLanguage(String abbreviation){
        String langName;
        switch (abbreviation){
            case "en" : langName="English"; break;
            case "fr" : langName="Français"; break;
            case "ru" : langName="Руски"; break;
            case "de" : langName="Deutsch"; break;
            default:  langName="Unknown"; break;
        }
        return  langName;
    }


}
