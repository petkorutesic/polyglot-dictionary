package com.ggsoft.poliglot.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="LANGUAGE")
public class  Language{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(name="TYPE",  nullable=false)
	private String lang;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "language")
	@JsonIgnore
	private Set<Word> languageWords;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="language")
	@JsonIgnore
	private Set<Meaning> languageMeanings;
		/**
		 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getLang() {
		return lang;
	}


	public void setLang(String lang) {
		this.lang = lang;
	}


	public Set<Word> getLanguageWords() {
		return languageWords;
	}

	public void setLanguageWords(Set<Word> languageWords) {
		this.languageWords = languageWords;
	}

	public Set<Meaning> getLanguageMeanings() {
		return languageMeanings;
	}

	public void setLanguageMeanings(Set<Meaning> languageMeanings) {
		this.languageMeanings = languageMeanings;
	}
	
	public void addMeaning(Meaning m){
		this.getLanguageMeanings().add(m);
		m.setLanguage(this);
	}

	public void addWord(Word w){
		this.getLanguageWords().add(w);
		w.setLanguage(this);
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
		if (!(obj instanceof Language))
			return false;
		Language other = (Language) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if ( lang == null) {
			if (other.lang!= null)
				return false;
		} else if (!lang.equals(other.lang))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", lang=" + lang +  "]";
	}


	
}
