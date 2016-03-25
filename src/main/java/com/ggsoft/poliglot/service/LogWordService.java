package com.ggsoft.poliglot.service;

import com.ggsoft.poliglot.model.LogWord;
import com.ggsoft.poliglot.model.Word;

import java.util.List;


public interface LogWordService {

	LogWord findById(int id);
	
	void deleteLogWord(LogWord log);

	void deleteLogsForWord(Word w);

	List<LogWord> findLogsForWord(Word w);

	void updateLogWord(LogWord log);

	void saveLogWord(LogWord log);

	public void updateLogForWord(Word word);
	
}
