package com.ggsoft.poliglot.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="LOG_WORD", uniqueConstraints = {
@UniqueConstraint(columnNames = {"word_id", "time_visit"})})
public class  LogWord{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="WORD_ID")
	private Word word;

	@Column(name="TIME_VISIT", nullable=false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private DateTime timeVisit;

    @NotNull
    @Column(name="ACTIVE" , nullable = false)
	private Integer active;



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

	public DateTime getTimeVisit() {
		return timeVisit;
	}

	public void setTimeVisit(DateTime timeVisit) {
		this.timeVisit = timeVisit;
	}

	public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
	
}
