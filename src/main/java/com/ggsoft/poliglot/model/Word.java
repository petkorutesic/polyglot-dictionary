package com.ggsoft.poliglot.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="WORD")
public class  Word{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(name="CONTENT",  nullable=false)
	private String content;
	
	@NotNull
	@Column(name="TIME_CREATION" , nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private DateTime timeCreation;

	@NotNull
	@ManyToOne
	@JoinColumn(name="language_id")
	private Language language;
	
	@OrderBy("explanation")
	@OneToMany(fetch=FetchType.LAZY, mappedBy="word")
	@JsonIgnore
	private Set<Meaning> wordMeanings;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="word")
    @JsonIgnore
    private Set<LogWord> wordLogs;

    /**
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

	/**
	 * @return the word
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param word the word to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	

	public DateTime getTimeCreation() {
		return timeCreation;
	}

	public void setTimeCreation(DateTime timeCreation) {
		this.timeCreation = timeCreation;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Set<Meaning> getWordMeanings() {
		return wordMeanings;
	}

	public void setWordMeanings(Set<Meaning> wordMeanings) {
		this.wordMeanings = wordMeanings;
	}

    public Set<LogWord> getWordLogs() {
        return wordLogs;
    }

    public void setWordLogs(Set<LogWord> wordLogs) {
        this.wordLogs = wordLogs;
    }

	public void addMeaning(Meaning meaning){
		if (this.wordMeanings == null)
			this.wordMeanings= new HashSet<Meaning>();
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
		if (!(obj instanceof Word))
			return false;
		Word other = (Word) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if ( content == null) {
			if (other.content!= null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Word [id=" + id + ", content=" + content + ", timeCreation=" + timeCreation + ", language=" + language
				+  "]";
	}




	
}
