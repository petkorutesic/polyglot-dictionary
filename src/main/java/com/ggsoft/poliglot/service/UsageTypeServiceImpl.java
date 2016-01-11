package com.ggsoft.poliglot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggsoft.poliglot.dao.UsageTypeDao;
import com.ggsoft.poliglot.model.UsageType;




@Service("usageTypeService")
@Transactional
public class UsageTypeServiceImpl implements UsageTypeService{
	
	@Autowired
	UsageTypeDao dao;
	
	public UsageType findById(int id) {
		return dao.findById(id);
	}

	public List<UsageType> findAllUsageTypes() {
		return dao.findAllUsageTypes();
	}

	public void deleteUsageType(UsageType usageType){
		dao.delete(usageType);
	}

	public void saveUsageType(UsageType usageType){
		dao.save(usageType);
	}

	public void updateUsageType(UsageType usageType) {
		dao.save(usageType);
		
	}


}
