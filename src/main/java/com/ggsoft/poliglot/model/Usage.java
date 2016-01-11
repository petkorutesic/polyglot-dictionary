package com.ggsoft.poliglot.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="WORDUSAGE")
public class Usage {
	
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty
	@Column(name = "text", nullable = false)
	private String text;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "wordusage_wordusagetype", 
             joinColumns = { @JoinColumn(name = "wordusage_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "wordusagetype_id") })
	private Set<UsageType> usageTypes = new HashSet<UsageType>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<UsageType> getUsageTypes() {
		return usageTypes;
	}

	public void setUsageTypes(Set<UsageType> usageTypes) {
		this.usageTypes = usageTypes;
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
		Usage other = (Usage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
