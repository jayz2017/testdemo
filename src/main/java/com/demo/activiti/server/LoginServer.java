package com.demo.activiti.server;

import org.springframework.stereotype.Service;

import com.demo.activiti.entity.Staff;
import com.demo.activiti.entity.User;

/**
 * Login
 * @author jekk
 *
 */

public interface LoginServer {
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
