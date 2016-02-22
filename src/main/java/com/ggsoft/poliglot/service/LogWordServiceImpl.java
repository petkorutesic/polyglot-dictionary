package com.ggsoft.poliglot.service;

import com.ggsoft.poliglot.dao.LogWordDao;
import com.ggsoft.poliglot.model.LogWord;
import com.ggsoft.poliglot.model.Word;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
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
		//if there is some other log which is active set it inactive
		dao.saveLogWord(log);
	}
	public void updateLogForWord(Word word){
		List<LogWord> logs = dao.findLogsForWord(word);
		if (logs.isEmpty())
		{
			LogWord newLogWord = new LogWord();
			newLogWord.setWord(word);
			newLogWord.setTimeVisit(new DateTime());
			newLogWord.setActive(1);
			dao.saveLogWord(newLogWord);
		}
		else {
			Iterator<LogWord> l = logs.iterator();
            Boolean flagActiveFound = Boolean.FALSE;
			while(l.hasNext()){
				LogWord logWord = l.next();
				if (logWord.getActive()==1 && !flagActiveFound){
                    flagActiveFound = Boolean.TRUE;
					//if it visited some other day (not time but just date)
                    LocalDate currentDate = (new DateTime()).toLocalDate();
					if (currentDate.compareTo(logWord.getTimeVisit().toLocalDate())!=0)
					{
						logWord.setActive(0);
						dao.saveLogWord(logWord);
						LogWord newLogWord = new LogWord();
						newLogWord.setWord(word);
						newLogWord.setTimeVisit(new DateTime());
						newLogWord.setActive(1);
						dao.saveLogWord(newLogWord);
					}
				}else{
                    ;
                    //more then one active log - problem
                }

			}
		}

	}

}
