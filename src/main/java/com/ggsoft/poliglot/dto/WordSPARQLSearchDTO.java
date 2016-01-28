package com.ggsoft.poliglot.dto;

import com.ggsoft.poliglot.model.Language;


public class WordSPARQLSearchDTO {

    private String content;

    private Language language;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
