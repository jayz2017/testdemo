package com.demo.activiti.server;

public interface ITaskserver {
	/**
	 * 通过roleId查询该角色下的任务信息
	 * 
	 * @param roleId
	 * @return
	 */
	public Object findByRoleId(String roleId);

	/**
	 *根据taskId和roleId查询该任务的当前详细信息
	 * 
	 * @param taskId
	 * @param roleId
	 * @return
	 */
	public Object findByTaskIdAndRoleId(String taskId, String roleId);


	/**
	 *审核操作节点的任务，状态又通过，不通过，驳回，撤回
	 * 
	 * @param taskId
	 * @param roleId
	 * @param u_name
	 * @return
	 */
	public boolean updateByTaskId(String taskId, String roleId, String u_name, String operationType);

	/**
	 * 统计对应的节点数量
	 * 
	 * @param taskId
	 * @param roleId
	 * @return
	 */
	public int countTask(String taskId, String roleId, String taskType);

}
