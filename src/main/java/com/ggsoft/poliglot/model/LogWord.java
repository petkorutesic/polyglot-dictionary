package com.ggsoft.poliglot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="LOG_WORD", uniqueConstraints = {
@UniqueConstraint(columnNames = {"word_id", "time_visit"})})
public class  LogWord{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="WORD_ID")
	private Word word;

	@Column(name="TIME_VISIT", columnDefinition="DATETIME", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeWisit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	public Date getTimeWisit() {
		return timeWisit;
	}

	public void setTimeWisit(Date timeWisit) {
		this.timeWisit = timeWisit;
	}
	

	
}
