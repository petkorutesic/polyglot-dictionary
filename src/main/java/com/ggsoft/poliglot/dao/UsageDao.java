package com.ggsoft.poliglot.dao;

import java.util.List;

import com.ggsoft.poliglot.model.Usage;



public interface UsageDao {

	Usage findById(int id);
	
	Usage findByText(String text);
	
	List<Usage> findAllUsages();
	
	void save(Usage wordUsage);
	
	void deleteUsage(Usage wordUsage);
	
	
}

