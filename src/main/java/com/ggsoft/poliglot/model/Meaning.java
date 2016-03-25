package com.ggsoft.poliglot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name="MEANING")
public class Meaning {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Column(name="explanation")
	private String explanation;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="language_id")
	private Language language;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="word")
	private Word word;
	

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "MEANING_WORDTYPE", 
    	joinColumns = { @JoinColumn(name = "MEANING_ID") }, 
    	inverseJoinColumns = { @JoinColumn(name = "WORDTYPE_ID") })
	private Set<WordType> wordTypes = new HashSet<WordType>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "MEANING_WORDUSAGE", 
    	joinColumns = { @JoinColumn(name = "MEANING_ID") }, 
    	inverseJoinColumns = { @JoinColumn(name = "WORDUSAGE_ID") })
	private Set<Usage> wordUsages = new HashSet<Usage>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="meaningFrom")
	private Set<WordLink> fromLinks;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="meaningTo")
	private Set<WordLink> toLinks;
	
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

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
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

	public Set<WordLink> getFromLinks() {
		return fromLinks;
	}

	public void setFromLinks(Set<WordLink> fromLinks) {
		this.fromLinks = fromLinks;
	}

	public Set<WordLink> getToLinks() {
		return toLinks;
	}

	public void setToLinks(Set<WordLink> toLinks) {
		this.toLinks = toLinks;
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
		Meaning other = (Meaning) obj;
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
