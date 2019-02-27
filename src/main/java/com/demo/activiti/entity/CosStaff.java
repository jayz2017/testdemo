package com.demo.activiti.entity;

public class CosStaff {
	
	private String staff_id;
	private String staff_name;
	private String role;
	private String department;
	
	
	public CosStaff() {
		super();
	}
	public CosStaff(String staff_id, String staff_name, String role,
			String department) {
		super();
		this.staff_id = staff_id;
		this.staff_name = staff_name;
		this.role = role;
		this.department = department;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
}
