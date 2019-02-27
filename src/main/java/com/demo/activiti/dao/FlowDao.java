package com.demo.activiti.dao;

import java.util.List;

public interface FlowDao {
	public List<Object> findAll(String productType);
	public Object findOne(String workId);
	public String findWorkFlowNext();
}
