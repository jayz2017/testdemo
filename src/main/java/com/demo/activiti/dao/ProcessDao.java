package com.demo.activiti.dao;

public interface ProcessDao {
	/**
	 * �������
	 * @return
	 */
   public boolean DeployServer(String WorkId);
   /**
          * ɾ������
    * @return
    */
   public boolean deleteProcess(String WorkId);
   /**
   	* �޸�����
    * @return
    */
   public boolean UpdateProcess(String WorkId);
}
