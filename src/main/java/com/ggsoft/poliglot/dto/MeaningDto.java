package com.ggsoft.poliglot.dto;

import com.ggsoft.poliglot.model.Language;
import com.ggsoft.poliglot.model.Usage;
import com.ggsoft.poliglot.model.WordType;

import java.util.HashSet;
import java.util.Set;



public class MeaningDto {
	public MeaningDto() {
	}

	public MeaningDto( String explanation, Set<Usage> wordUsages) {
		this.explanation = explanation;
		this.wordUsages = wordUsages;
	}

	private Integer id;

	private String explanation;

	private Language language;

	private WordDto word;

	private Set<WordType> wordTypes = new HashSet<WordType>();

	private Set<Usage> wordUsages = new HashSet<Usage>();

	//Getters and setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public WordDto getWord() {
		return word;
	}

	public void setWord(WordDto word) {
		this.word = word;
	}
	
	public Set<WordType> getWordTypes(){
		return this.wordTypes;
	}
	
	public void setWordTypes(Set<WordType> wordTypes){
		this.wordTypes = wordTypes;
	}
	
	public Set<Usage> getWordUsages() {
		return wordUsages;
	}

	public void setWordUsages(Set<Usage> wordUsages) {
		this.wordUsages = wordUsages;
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
		if (getClass() != obj.getClass())
			return false;
		MeaningDto other = (MeaningDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (explanation == null){
			if (other.explanation !=null)
				return false;
		}else if (!explanation.equals(other.explanation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Meaning [explanation=" + explanation + ", language=" + language + ", word=" + word + "]";
	}




}
