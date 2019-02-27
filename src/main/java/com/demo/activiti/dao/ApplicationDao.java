package com.demo.activiti.dao;

import java.util.List;
import java.util.Map;

import com.demo.activiti.entity.CosApplication;

public interface ApplicationDao {
	public int insertOrderInfo(CosApplication cosApplication);

	public boolean UpdateOperationType(Map<String,Object> map);

	public boolean selectOneApplication(String profileId);

	public List<CosApplication> selectApplicationList(CosApplication applicationId);

	public int updateApplicationInformation();


	public Object selectApplicationRecordInformation();

	public  CosApplication findByprocessId(String processInstanceId);
	
	int UpdateProcessId(Map<String,Object> map);
}
