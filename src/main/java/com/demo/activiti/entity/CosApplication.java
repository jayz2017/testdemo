package com.demo.activiti.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CosApplication {
	private String application_id;
	private String profile_id;
	private String application_status;
	private String aplication_cretor;// 应用处理人
	private String current_operator;// 当前操作处理人
	private String form_version;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date submit_time;
	private String lastMdfyOperator;// 下一个操作者
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastMdfyTime;
	private String priority;// 优先级
	private String product_type;// 产品类型
	private String work_id;
	private String process_id;

	public String getProcess_id() {
		return process_id;
	}

	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}

	public String getWork_id() {
		return work_id;
	}

	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getApplication_id() {
		return application_id;
	}

	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}

	public String getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(String profile_id) {
		this.profile_id = profile_id;
	}

	public String getApplication_status() {
		return application_status;
	}

	public void setApplication_status(String application_status) {
		this.application_status = application_status;
	}

	public String getAplication_cretor() {
		return aplication_cretor;
	}

	public void setAplication_cretor(String aplication_cretor) {
		this.aplication_cretor = aplication_cretor;
	}

	public String getCurrent_operator() {
		return current_operator;
	}

	public void setCurrent_operator(String current_operator) {
		this.current_operator = current_operator;
	}

	public String getForm_version() {
		return form_version;
	}

	public void setForm_version(String form_version) {
		this.form_version = form_version;
	}

	public Date getSubmit_time() {
		return submit_time;
	}

	public void setSubmit_time(Date submit_time) {
		this.submit_time = submit_time;
	}

	public String getLastMdfyOperator() {
		return lastMdfyOperator;
	}

	public void setLastMdfyOperator(String lastMdfyOperator) {
		this.lastMdfyOperator = lastMdfyOperator;
	}

	public Date getLastMdfyTime() {
		return lastMdfyTime;
	}

	public void setLastMdfyTime(Date lastMdfyTime) {
		this.lastMdfyTime = lastMdfyTime;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

}
