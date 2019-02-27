package com.demo.activiti.controller;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.activiti.util.ObjectUtils;

@RestController("test")
@ResponseBody
public class TestController {
	@Resource
	RepositoryService repositoryService;

	@Resource
	RuntimeService runtimeService;

	@Resource
	TaskService taskService;

	@Resource
	HistoryService historyService;
   /**
    * 部署
    * @return
    */
	@RequestMapping(value="deploy",method=RequestMethod.POST)
	public boolean deploy() {
		Deployment deployment =	repositoryService
										.createDeployment()
										.addClasspathResource("")
										.addClasspathResource("")
										.name("")
										.deploy();
		if(!ObjectUtils.isNullOrEmpty(deployment)) {
			System.out.println(deployment.getName());
			System.out.println(deployment.getTenantId());
			System.out.println(deployment.getId());
			System.out.println(deployment.getDeploymentTime());
			return true;
		}
		return false;
   }
	/**
	 * 流程启动
	 * @param workFlowname
	 * @return
	 */
	@RequestMapping(value="start",method=RequestMethod.POST)
	public boolean start(String workFlowname) {
		ProcessInstance processInstance =	runtimeService.startProcessInstanceByKey(workFlowname);
		if(!ObjectUtils.isNullOrEmpty(processInstance)) {
			System.out.println("流程名称"+processInstance.getName());
			System.out.println("流程ID"+processInstance.getId());
			System.out.println("流程BusinessKey"+processInstance.getBusinessKey());
			System.out.println("流程定义的Key"+processInstance.getProcessDefinitionKey());
			return true;
		}
		return false;
	}
	@RequestMapping(value="complete",method=RequestMethod.POST)
	public boolean complete(String processDefinitionId) {
		taskService.createTaskQuery().processDefinitionId(processDefinitionId);
		return false;
		
	}
}
