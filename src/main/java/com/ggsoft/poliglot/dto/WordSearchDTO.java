package com.ggsoft.poliglot.dto;

import com.ggsoft.poliglot.model.Language;
import org.hibernate.annotations.Type;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by info on 21.1.16..
 */
public class WordSearchDTO {

    public enum WORD_SEARCH_MODE {
        SIMPLE("SIMPLE"),
        DATE("DATE"),
        NUM_VISIT("NUM_VISIT"),
        NUM_VISIT_RANDOM("NUM_VISIT_RANDOM"),
        LAST_VISIT_ASCENDING_TIME("LAST_VISIT_ASCENDING_TIME"),
        LAST_VISIT_DESCENDING_TIME("LAST_VISIT_DESCENDING_TIME"),
        TIME_CREATION_ASCENDING("TIME_CREATION_ASCENDING"),
        TIME_CREATION_DESCENDING("TIME_CREATION_DESCENDING");


        private static final Map<String, WORD_SEARCH_MODE> codeToSearchModeMap = new HashMap<>();

        static {
            for (WORD_SEARCH_MODE type : WORD_SEARCH_MODE.values()) {
                codeToSearchModeMap.put(type.code, type);
            }
        }

        private final String code;

        WORD_SEARCH_MODE(String code) {
            this.code = code;
        }

        public String getWordSearchCode(){return code;}

        public static WORD_SEARCH_MODE getWordSearchType(int code) {return codeToSearchModeMap.get(code);}

        public static List<WORD_SEARCH_MODE> getAllSearchModes (){
            List<WORD_SEARCH_MODE> allModes = new ArrayList<WORD_SEARCH_MODE>();

            for (WORD_SEARCH_MODE type : WORD_SEARCH_MODE.values()) {
                allModes.add(type);
            }
            return allModes;
        }
    }

    private String content;

    private WORD_SEARCH_MODE searchMode;

    private Integer numberOfVisits;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime fromDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime untilDate;

    private Set<Language> languages;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public WORD_SEARCH_MODE getSearchMode() {
        return searchMode;
    }

    public void setSearchMode(WORD_SEARCH_MODE searchMode) {
        this.searchMode = searchMode;
    }
    public Integer getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(Integer numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public DateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(DateTime fromDate) {
        this.fromDate = fromDate;
    }

    public DateTime getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(DateTime untilDate) {
        this.untilDate = untilDate;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "WordSearchDTO{" +
                "content='" + content + '\'' +
                ", searchMode='" + searchMode + '\'' +
                ", numberOfVisits=" + numberOfVisits +
                ", fromDate=" + fromDate +
                ", untilDate=" + untilDate +
                '}';
    }
}
