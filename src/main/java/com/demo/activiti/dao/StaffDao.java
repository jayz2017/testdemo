package com.demo.activiti.dao;

import com.demo.activiti.entity.Staff;

public interface StaffDao {
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
