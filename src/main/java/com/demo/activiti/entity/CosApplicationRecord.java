package com.demo.activiti.entity;

import java.util.Date;
/**
 * 日志表
 * @author jekk
 *
 */

public class CosApplicationRecord {
	
	private String application_id;
	private String department_id;
	private String staff_id;
	private String operate_type;
	private String operation_comment;
	private Date operation_time;
	
	public String getApplication_id() {
		return application_id;
	}
	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}
	public String getOperation_comment() {
		return operation_comment;
	}
	public void setOperation_comment(String operation_comment) {
		this.operation_comment = operation_comment;
	}
	public Date getOperation_time() {
		return operation_time;
	}
	public void setOperation_time(Date operation_time) {
		this.operation_time = operation_time;
	}
	
	
}
