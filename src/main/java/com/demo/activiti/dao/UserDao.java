package com.demo.activiti.dao;

import com.demo.activiti.entity.Staff;
import com.demo.activiti.entity.User;

public interface UserDao {

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
   public String findByUsername(String username);

}
