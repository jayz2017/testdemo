package com.demo.activiti.server;

public interface RoleServer {
	
	/**
	 * ��ɫȨ��ģ��
	 * @return
	 */
   //��ѯԱ����ɫ
   public Object selectStaffRole();
   //��ѯԱ��Ȩ��
   public Object selectStaffPermission();
   //��ѯԱ������
   public Object selectStaffDepartment();
   

}
