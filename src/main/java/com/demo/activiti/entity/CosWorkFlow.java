package com.demo.activiti.entity;

public class CosWorkFlow {
	
	private String work_flow_id;
	private String privious;
	private String self;
	private String next;
	private String level;
	
	
	public CosWorkFlow() {
		super();
	}
	
	public CosWorkFlow(String work_flow_id, String privious, String self,
			String next, String level) {
		super();
		this.work_flow_id = work_flow_id;
		this.privious = privious;
		this.self = self;
		this.next = next;
		this.level = level;
	}

	public String getWork_flow_id() {
		return work_flow_id;
	}
	public void setWork_flow_id(String work_flow_id) {
		this.work_flow_id = work_flow_id;
	}
	public String getPrivious() {
		return privious;
	}
	public void setPrivious(String privious) {
		this.privious = privious;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	

}
