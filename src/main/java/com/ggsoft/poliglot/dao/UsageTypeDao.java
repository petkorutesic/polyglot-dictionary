package com.ggsoft.poliglot.dao;

import java.util.List;

import com.ggsoft.poliglot.model.UsageType;




public interface UsageTypeDao {

	UsageType findById(int id);
	
	UsageType findByText(String text);
	
	List<UsageType> findAllUsageTypes();
	
	void save(UsageType wordUsageType);
	
	void delete(UsageType wordUsageType);
	
	
}

