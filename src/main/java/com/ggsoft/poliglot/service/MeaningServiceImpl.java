package com.ggsoft.poliglot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggsoft.poliglot.dao.MeaningDao;
import com.ggsoft.poliglot.model.Meaning;
import com.ggsoft.poliglot.model.Word;


@Service("meaningService")
@Transactional
public class MeaningServiceImpl implements MeaningService{
	
	@Autowired
	MeaningDao dao;
	
	public Meaning findById(int id) {
		return dao.findById(id);
	}
	
	public Meaning findByIdSimple(int id) {
		return dao.findByIdSimple(id);
	}

	public Meaning findByMeaning(String type){
		return dao.findByMeaning(type);
	}

	public List<Meaning> findMeaningsForWord(Word w) {
		return dao.findMeaningsForWord(w);
	}
	public List<Meaning> findAllMeanings() {
		return dao.findAllMeanings();
	}

	public void saveMeaning(Meaning meaning){
		dao.save(meaning);
	}

	public void updateMeaning(Meaning meaning) {
		dao.update(meaning);
	}

	public void deleteMeaning(Meaning meaning) {
		dao.deleteMeaning(meaning);
	}
}
