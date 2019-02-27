package com.demo.activiti.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class processVariablesTest {

	@Resource
	private TaskService taskService;

	private RuntimeService runtimeService;

	/*
	 * 设置流程变量的值
	 */
	public void setProcessVariables() {
		// 设置流程变量
		/*
		 * //表示使用对象id，和流程变量的名称，设置流程变量的值（一次只能设置一个）
		 * runtimeService.setVariable(executionId, variableName, value);
		 * //表示使用对象id，和Map集合，设置流程变量的值（map集合的key就是流程变量的名称，value就额是流程变量的值）
		 * runtimeService.setVariables(executionId, variables); //通过任务id设置流程变量
		 * taskService.setVariable(taskId, variableName, value); //启动运行时也可以设置
		 * runtimeService.startProcessInstanceByKey(processDefinitionKey, variables);
		 * //完成任务的时候也可以设置 taskService.complete(taskId, variables);
		 */

		/* 获取流程变量 */
		/*
		 * runtimeService.getVariable(executionId,
		 * variableName);//使用执行对象ID和流程变量的名称，获取流程变量的值
		 * runtimeService.getVariables(executionId);//使用执行对象的ID，获取所有的流程变量到Map集合中
		 * runtimeService.getVariables(executionId,
		 * variableNames);//使用执行对象的ID，获取流程变量的值，通过设置流程变量名称存放到集合中获取指定的流程变量的值
		 * taskService.getVariable(taskId, variableName);//使用执行对象ID和流程变量的名称，获取流程变量的值
		 * taskService.getVariables(taskId);//使用执行对象的ID，获取所有的流程变量到Map集合中
		 * taskService.getVariables(taskId,
		 * variableNames);//使用执行对象的ID，获取流程变量的值，通过设置流程变量名称存放到集合中获取指定的流程变量的值
		 */
	}

	/**
	 * 设置流程变量
	 */
	public void setvariables() {
		// 任务ID
		String taskId = "";
		/* 设置流程变量，使用基本数据类型 */
		taskService.setVariable(taskId, "请假天数", 3);
		taskService.setVariable(taskId, "请假原因", "回家相亲");
		taskService.setVariable(taskId, "请假时间", new Date());
		// 将以上数据添加到流程变量表act_ru_variable 同时流程历史表中也存在act_hi_varinst
	}

	public void getvariables(String taskId) {
		taskService.getVariable(taskId, "请假原因");
	}
}
