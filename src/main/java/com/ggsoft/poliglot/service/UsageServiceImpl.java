package com.ggsoft.poliglot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggsoft.poliglot.dao.UsageDao;
import com.ggsoft.poliglot.model.Usage;




@Service("usageService")
@Transactional
public class UsageServiceImpl implements UsageService{
	
	@Autowired
	UsageDao dao;
	
	public Usage findById(int id) {
		return dao.findById(id);
	}

	public List<Usage> findAllUsages() {
		return dao.findAllUsages();
	}

	public void deleteUsage(Usage wordUsage){
		dao.deleteUsage(wordUsage);
	}

	public void saveUsage(Usage wordUsage){
		dao.save(wordUsage);
	}

	public void updateUsage(Usage wordUsage) {
		dao.save(wordUsage);
		
	}


}
