package com.demo.activiti.server;

import java.util.List;

public interface ActReDeploymentServer {
	/**
	 * 获取部署流的所有名称
	 * 
	 * @return
	 */
	public List<Object> findAll();
}
