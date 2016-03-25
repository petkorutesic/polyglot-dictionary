package com.ggsoft.poliglot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;

@Entity
@Table(name = "WORDLINK")
public class WordLink {
	private static final Logger logger = Logger.getLogger(WordLink.class);
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "meaning_from")
	private Meaning meaningFrom;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "meaning_to")
	private Meaning meaningTo;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "link_type")
	private LinkType linkType;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LinkType getLinkType() {
		return linkType;
	}

	public void setLinkType(LinkType linkType) {
		this.linkType = linkType;
	}

	public Meaning getMeaningFrom() {
		return meaningFrom;
	}

	public void setMeaningFrom(Meaning meaningFrom) {
		this.meaningFrom = meaningFrom;
	}

	public Meaning getMeaningTo() {
		return meaningTo;
	}

	public void setMeaningTo(Meaning meaningTo) {
		this.meaningTo = meaningTo;
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
		logger.info("Comparison of wordlink items.");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordLink other = (WordLink) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
	
	

	

}
