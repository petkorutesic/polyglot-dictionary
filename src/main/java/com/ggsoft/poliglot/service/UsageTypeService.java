package com.ggsoft.poliglot.service;

import java.util.List;

import com.ggsoft.poliglot.model.UsageType;



public interface UsageTypeService {

	UsageType findById(int id);
	
	void deleteUsageType(UsageType type);

	List<UsageType> findAllUsageTypes();

	void updateUsageType(UsageType usageType);

	void saveUsageType(UsageType usageType);
	
}
