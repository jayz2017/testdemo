package com.demo.activiti.server;

import java.util.List;

import org.activiti.engine.repository.Deployment;

public interface ProcessService {
	public boolean DeployServer(String WorkId, String deployFlowName);

	public boolean deleteProcess(String WorkId);

	public boolean UpdateProcess(String WorkId);

	public List<Deployment> selectProcessList(String name, int startPage, int maxResults);

}
