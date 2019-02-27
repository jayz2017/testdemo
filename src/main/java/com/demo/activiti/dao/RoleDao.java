package com.demo.activiti.dao;

public interface RoleDao {
	  /**
	   * 查询用户角色
     * @param username
     * @return
     */
    public Object findByUsername(String username);
}
