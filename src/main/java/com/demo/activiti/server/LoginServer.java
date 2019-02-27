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
	 * ��ѯ�û����ڲ�����
	 * @param username
	 * @param passsword
	 * @return
	 */
     public Staff findByUsername(Staff staff) ;
     /**
	   * ��ѯ�û���ɫ
    * @param username
    * @return
    */
   public String findRole(String username);
}
