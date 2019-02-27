package com.demo.activiti.server.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.activiti.server.ApplicationServer;
import com.demo.activiti.server.TaskFlowServer;

import net.sf.json.JSONObject;

@Service("TaskFlowServer")
public class TaskFlowServerImpl implements TaskFlowServer {

	@Resource
	private RepositoryService repositoryService;

	@Resource
	private RuntimeService runtimeService;

	@Resource
	private TaskService taskService;

	@Resource
	private ApplicationServer applicationServer;

	/**
	 * 启动流程
	 */
	@Override
	@Transactional
	public boolean StartTask(String deploymentKey, String applicationId) {
		try {
			// 启动流程定义
			/*ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
					.deploymentId(deploymentId).singleResult();*/
			// 获取流程信息
			//ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(deploymentKey);
			// 启动流程服务
			Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId())
					.singleResult();
			// 完成流程操作
			// taskService.complete(task.getId());
			// 启动流程的时候去修改Application表中对应信息的状态，由原来的待审核到现在的审核中
			// applicationServer.UpdateOperationType(applicationId,
			// BizCode.Status.REVIEWING);
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("process_id", task.getProcessInstanceId());
			map.put("application_id", applicationId);
			applicationServer.UpdateProcessId(map);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 修改流程状态
	 * 
	 * @param taskId
	 * @param remarks   流程批注意ע
	 * @param state     流程处理操作状态值
	 * @param condition 流程控制条件
	 */
	@Override
	public boolean UpdateTaskType(JSONObject object) {
		String taskId = object.getString("taskId");
		String remarks = object.getString("remarks");
		String state = object.getString("state");
		String username = object.getString("username");
		// String userRole = object.getString("userRole");
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Map<String, Object> map = new HashMap<String, Object>();

		String processInstanceId = task.getProcessInstanceId();
		// 给表act_hi_comment添加user_ID
		Authentication.setAuthenticatedUserId(username);
		// 添加流程操作信息
		taskService.addComment(taskId, processInstanceId, remarks);
		if (state.equals("1")) {
			//map.put("msg", "通过");
			map.put("msg", "pass");
		} else {
			//map.put("msg", "未通过");
			map.put("msg", "Reject");
		}
		// 判断条件存在不
		if (object.containsKey("condition")) {
			String condition = object.getString("condition");
			map.put("condition", condition);
		}
		// 完成流程操作
		try {
			taskService.complete(taskId, map);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean StartTask1(String workFlowName, String applicationId) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(workFlowName);
		// 启动流程服务
					Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId())
							.singleResult();
		return false;
	}

}
