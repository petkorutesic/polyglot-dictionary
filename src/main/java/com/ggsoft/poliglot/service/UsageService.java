package com.ggsoft.poliglot.service;

import java.util.List;

import com.ggsoft.poliglot.model.Usage;



public interface UsageService {

	Usage findById(int id);
	
	void deleteUsage(Usage type);

	List<Usage> findAllUsages();

	void updateUsage(Usage wordUsage);

	void saveUsage(Usage wordUsage);
	
}
