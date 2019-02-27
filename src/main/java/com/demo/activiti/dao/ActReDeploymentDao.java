package com.demo.activiti.dao;

import java.util.List;

public interface ActReDeploymentDao {
	/**
	 * 获取部署流的所有名称
	 * 
	 * @return
	 */
	public List<Object> findAll();
}
