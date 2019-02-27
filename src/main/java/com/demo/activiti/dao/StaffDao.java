package com.demo.activiti.dao;

import com.demo.activiti.entity.Staff;

public interface StaffDao {
	/**
	 * 查询用户存在不存在
	 * @param username
	 * @param passsword
	 * @return
	 */
     public Staff findByUsername(Staff staff) ;
     /**
	   * 查询用户角色
    * @param username
    * @return
    */
   public String findRole(String username);
}
