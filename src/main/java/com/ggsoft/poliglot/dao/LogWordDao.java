package com.ggsoft.poliglot.dao;

import com.ggsoft.poliglot.model.LogWord;
import com.ggsoft.poliglot.model.Word;

import java.util.List;


public interface LogWordDao {

	LogWord findById(int id);

	void deleteLogsForWord(Word w);

	List<LogWord> findLogsForWord(Word w);

/* List<LogWord> findAllLogWords(); */
	
	void saveLogWord(LogWord wordLogWord);
	
	void deleteLogWord(LogWord wordLogWord);
	
	
}

