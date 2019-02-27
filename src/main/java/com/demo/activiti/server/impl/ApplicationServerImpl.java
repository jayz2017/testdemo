package com.demo.activiti.server.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.activiti.dao.ApplicationDao;
import com.demo.activiti.dao.ApplicationRecordDao;
import com.demo.activiti.entity.CosApplication;
import com.demo.activiti.entity.CosApplicationRecord;
import com.demo.activiti.server.ApplicationServer;
import com.demo.activiti.server.TaskFlowServer;
import com.demo.activiti.util.DateUtils;
import com.demo.activiti.util.ObjectUtils;
import com.demo.activiti.util.StringUtils;

@Service("ApplicationServer")
public class ApplicationServerImpl implements ApplicationServer {
	@Resource
	public ApplicationDao applicationDao;
	@Resource
	public ApplicationRecordDao info;
	@Resource
	public TaskFlowServer taskFlowServer;

	@Override
	@Transactional
	public boolean insertOrderInfo(JSONArray jsonArray) {
		int numer = 0;
		if (jsonArray.size() > 0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				if (!ObjectUtils.isNullOrEmpty(jsonObject)) {
					CosApplication cosApplication = JSONObject.toJavaObject(jsonObject, CosApplication.class);
					cosApplication.setApplication_id(StringUtils.randomUUID());
					cosApplication.setApplication_status("待审批");
					cosApplication.setSubmit_time(DateUtils.parseToDate());
					numer = applicationDao.insertOrderInfo(cosApplication);
					numer += numer;
				}
			}
		}
		if (numer > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * @Override public Object findByRoleIdaAndOperationId() { // TODO
	 * Auto-generated method stub return
	 * applicationDao.findByRoleIdaAndOperationId(); }
	 */

	@Override
	public boolean UpdateOperationType(String applcationId, int applicationStatus) {
		CosApplicationRecord cosApplicationRecord = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applcationId", applcationId);
		map.put("applicationStatus", applicationStatus);
		CosApplication cosApplication = new CosApplication();
		cosApplication.setApplication_id(applcationId);
		List<CosApplication> list = applicationDao.selectApplicationList(cosApplication);
		if (list.size() > 0 && !list.isEmpty()) {
			for (CosApplication cos : list) {
				cosApplicationRecord = new CosApplicationRecord();
				cosApplicationRecord.setApplication_id(cos.getApplication_id());
				cosApplicationRecord.setDepartment_id("");
				cosApplicationRecord.setOperate_type(cos.getApplication_status());
			}
		}
		return applicationDao.UpdateOperationType(map);
	}

	@Override
	public List<CosApplication> selectApplicationList(String applicationId) {
		// TODO Auto-generated method stub
		CosApplication cosApplication = new CosApplication();
		cosApplication.setApplication_id(applicationId);
		return applicationDao.selectApplicationList(cosApplication);
	}

	@Override
	public Object selectApplicationRecordInformation() {
		// TODO Auto-generated method stub
		return applicationDao.selectApplicationRecordInformation();
	}

	@Override
	public Object selectApplicationOperateList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertApplicationOperateRecord(CosApplicationRecord ApplicationRecord) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public boolean insertOrderInfo1(CosApplication cosApplication) {
		int numer = 0;
		cosApplication.setApplication_id(StringUtils.randomUUID());
		cosApplication.setApplication_status("待审批");
		cosApplication.setSubmit_time(DateUtils.parseToDate());
		numer = applicationDao.insertOrderInfo(cosApplication);
		if (numer > 0) {
			boolean flag = taskFlowServer.StartTask(cosApplication.getWork_id(), cosApplication.getApplication_id());
			if (flag) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public CosApplication finfByProcessId(String processInstanceId) {
		return applicationDao.findByprocessId(processInstanceId);
	}

	@Override
	public int UpdateProcessId(Map<String, Object> map) {
		return applicationDao.UpdateProcessId(map);
	}

}
