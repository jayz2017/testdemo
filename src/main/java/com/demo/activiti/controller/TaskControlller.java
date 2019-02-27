package com.demo.activiti.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.activiti.server.TaskFlowServer;

import net.sf.json.JSONObject;
/**
 * 流程操作
 * @author 陈浩
 *
 */
@RestController
@RequestMapping("taskExcute")
public class TaskControlller {
	
	@Resource
	public TaskFlowServer taskFlowServer;
	
	@Resource
	TaskService taskService;
	/**
	 *启动流程
	 * 
	 * @param procecssId
	 * @returnworkFlowId
	 */
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	@ResponseBody
	public boolean StartTask(@RequestParam String workFlowName, @RequestParam String applicationId) {
		return taskFlowServer.StartTask(workFlowName,applicationId);
	}

	/**
	 *修改流程节点状态
	 */
	@RequestMapping(value = "UpdateTaskType", method = RequestMethod.POST)
	@ResponseBody
	public boolean UpdateTaskType(String taskId,String comment ,String state,String username) {
		JSONObject object = new JSONObject();
		object.put("taskId", taskId);
		object.put("remarks", comment);
		object.put("state", state);
		object.put("username", username);
		return taskFlowServer.UpdateTaskType(object);
	}
	
	@RequestMapping(value = "redirectPage", method = RequestMethod.POST)
	@ResponseBody
	public Object redirectPage() {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("url", "audit_bz.jsp");
		return map;
	}
	//可以分配个人任务从一个人到另外一个人（认领任务）
	public void setAssigneeTask() {
		String taskId="";
		//指定任务办理人
		taskService.setAssignee(taskId, "userId");
		//改变的时act_ru_task表中的assignee字段
	}
}
