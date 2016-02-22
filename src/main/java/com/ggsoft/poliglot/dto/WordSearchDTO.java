package com.ggsoft.poliglot.dto;

import org.hibernate.annotations.Type;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by info on 21.1.16..
 */
public class WordSearchDTO {

    private String content;

    private String searchMode;

    private Integer numberOfVisits;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime fromDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime untilDate;



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSearchMode() {
        return searchMode;
    }

    public void setSearchMode(String searchMode) {
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
