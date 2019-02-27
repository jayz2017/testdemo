package com.demo.activiti.dao;

import com.demo.activiti.entity.Staff;
import com.demo.activiti.entity.User;

public interface UserDao {

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
   public String findByUsername(String username);

}
