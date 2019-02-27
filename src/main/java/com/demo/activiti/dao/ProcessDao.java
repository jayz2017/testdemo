package com.demo.activiti.dao;

public interface ProcessDao {
	/**
	 * 部署服务
	 * @return
	 */
   public boolean DeployServer(String WorkId);
   /**
          * 删除流程
    * @return
    */
   public boolean deleteProcess(String WorkId);
   /**
   	* 修改流程
    * @return
    */
   public boolean UpdateProcess(String WorkId);
}
