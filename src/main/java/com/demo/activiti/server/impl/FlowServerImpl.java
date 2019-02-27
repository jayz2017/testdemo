package com.demo.activiti.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.activiti.dao.FlowDao;
import com.demo.activiti.server.FlowServer;
@Service("FlowServer")
public class FlowServerImpl implements FlowServer{
    
	@Resource
	private FlowDao flowDao;
	@Override
	public List<Object> findAll(String productType) {
		// TODO Auto-generated method stub
		return flowDao.findAll(productType);
	}

	@Override
	public Object findOne(String workId) {
		// TODO Auto-generated method stub
		return flowDao.findOne(workId);
	}

	@Override
	public String findWorkFlowNext() {
		// TODO Auto-generated method stub
		return null;
	}

}
