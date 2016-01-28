package com.ggsoft.poliglot.service;

import com.ggsoft.poliglot.dao.LogWordDao;
import com.ggsoft.poliglot.model.LogWord;
import com.ggsoft.poliglot.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("logWordService")
@Transactional
public class LogWordServiceImpl implements LogWordService{
	
	@Autowired
	LogWordDao dao;
	
	public LogWord findById(int id) {
		return dao.findById(id);
	}

	public List<LogWord> findLogsForWord(Word w) {
		return dao.findLogsForWord(w);
	}

	public void deleteLogWord(LogWord log){
		dao.deleteLogWord(log);
	}

	public void saveLogWord(LogWord log){
		dao.saveLogWord(log);
	}

	public void updateLogWord(LogWord log) {
		dao.saveLogWord(log);
		
	}


}
