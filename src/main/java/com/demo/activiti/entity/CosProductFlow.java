package com.demo.activiti.entity;

public class CosProductFlow {
	
	private String product_type;
	private String work_flow_id;
	
	
	public CosProductFlow() {
		super();
	}
	
	public CosProductFlow(String product_type, String work_flow_id) {
		super();
		this.product_type = product_type;
		this.work_flow_id = work_flow_id;
	}

	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getWork_flow_id() {
		return work_flow_id;
	}
	public void setWork_flow_id(String work_flow_id) {
		this.work_flow_id = work_flow_id;
	}
	

}
