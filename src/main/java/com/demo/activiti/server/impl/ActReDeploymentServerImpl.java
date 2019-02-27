package com.demo.activiti.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.activiti.dao.ActReDeploymentDao;
import com.demo.activiti.server.ActReDeploymentServer;

@Service("ActReDeploymentServer")
public class ActReDeploymentServerImpl implements ActReDeploymentServer {
	@Resource
	public ActReDeploymentDao actReDeploymentDao;

	@Override
	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return actReDeploymentDao.findAll();
	}

}
