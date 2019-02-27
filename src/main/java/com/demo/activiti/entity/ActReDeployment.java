package com.demo.activiti.entity;

/**
 * activi 生产的流部署表
 * 
 * @author chenhao
 *
 */
public class ActReDeployment {
	private String id;
	private String name;
	private String cate_gory;
	private String tenant_id;
	private String deploy_time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCate_gory() {
		return cate_gory;
	}

	public void setCate_gory(String cate_gory) {
		this.cate_gory = cate_gory;
	}

	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getDeploy_time() {
		return deploy_time;
	}

	public void setDeploy_time(String deploy_time) {
		this.deploy_time = deploy_time;
	}

}
