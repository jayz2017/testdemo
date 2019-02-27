package com.demo.activiti.server.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.activiti.dao.StaffDao;
import com.demo.activiti.entity.Staff;
import com.demo.activiti.server.LoginServer;
@Service("LoginServer")
public class LoginServerImpl implements LoginServer{
	@Resource
	private StaffDao StaffDao;
	@Override
	public Staff findByUsername(Staff staff) {
		// TODO Auto-generated method stub
		return StaffDao.findByUsername(staff);
	}

	@Override
	public String findRole(String username) {
		// TODO Auto-generated method stub
		return StaffDao.findRole(username);
	}
	


}
