package com.demo.activiti.server;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.demo.activiti.entity.CosApplication;
import com.demo.activiti.entity.CosApplicationRecord;

public interface ApplicationServer {
	public boolean insertOrderInfo(JSONArray cosApplication);

	public boolean UpdateOperationType(String profileId, int applicationStatus);

	//public boolean selectOneApplication(String profileId);

	public List<CosApplication> selectApplicationList(String applicationId);

	public int insertApplicationOperateRecord(CosApplicationRecord ApplicationRecord);

	public Object selectApplicationOperateList();

	public Object selectApplicationRecordInformation();

	public boolean insertOrderInfo1(CosApplication cosApplication);

	public CosApplication finfByProcessId(String processInstanceId);
	int UpdateProcessId(Map<String,Object> map);
}
