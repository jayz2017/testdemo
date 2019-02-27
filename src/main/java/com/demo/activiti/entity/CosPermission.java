package com.demo.activiti.entity;

public class CosPermission {
	
	private String role_id;
	private String permission;
	
	
	public CosPermission() {
		super();
	}
	public CosPermission(String role_id, String permission) {
		super();
		this.role_id = role_id;
		this.permission = permission;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
