package com.demo.activiti.server;

import java.util.List;

public interface FlowServer {
	/**
	 * ��ȡ���������б�
	 * 
	 * @param productType
	 * @return
	 */
	public List<Object> findAll(String productType);

	/**
	 * ��ȡ��Ӧ�Ĺ�������Ϣ
	 * @param workId
	 * @return
	 */
	public Object findOne(String workId);

	/**
	 * ��ȡ��һ���̽ڵ�
	 * 
	 * @return
	 */
	public String findWorkFlowNext();
}
