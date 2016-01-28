package com.ggsoft.poliglot.dto;

import com.ggsoft.poliglot.model.Language;

import java.util.HashSet;
import java.util.Set;

public class WordDto {

    private Integer id;

    private String content;


    private Language language;

    private Set<MeaningDto> wordMeanings;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


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

    public Set<MeaningDto> getWordMeanings() {
        return wordMeanings;
    }

    public void setWordMeanings(Set<MeaningDto> wordMeanings) {
        this.wordMeanings = wordMeanings;
    }


    public void addMeaning(MeaningDto meaning) {
        if (this.wordMeanings == null)
            this.wordMeanings = new HashSet<MeaningDto>();
        this.wordMeanings.add(meaning);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof WordDto))
            return false;
        WordDto other = (WordDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "WordDto [id=" + id + ", content=" + content + ",  language=" + language
                + "]";
    }

}