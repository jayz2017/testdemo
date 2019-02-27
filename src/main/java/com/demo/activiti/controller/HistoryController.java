package com.demo.activiti.controller;

import javax.annotation.Resource;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.activiti.server.ApplicationServer;
import com.demo.activiti.server.HistoryServer;

@RestController
@RequestMapping("History")
public class HistoryController {
	@Resource
	private TaskService taskService;
	@Resource
	public HistoryServer historyServer;
	
	@Resource
	public ApplicationServer applicationServer;

	@RequestMapping("listHistoryCommentWithProcessInstanceId")
	@ResponseBody
	public Object listHistoryCommentWithProcessInstanceId(String processInstanceId) throws Exception {
		return historyServer.listHistoryCommentWithProcessInstanceId(processInstanceId);

	}
	/**
	 * 查询个人代办任务
	 * @param assigneeRoleName
	 * @return
	 */
	@RequestMapping(value="findMyPersonalTask",method=RequestMethod.POST)
	@ResponseBody
	public  Object findMyPersonalTask(@RequestParam String assigneeRoleName) {
		return historyServer.findMyPersonalTask(assigneeRoleName);
	}
	/**
	 * 完成个人任务
	 */
	/*public Object completeMypersonalTask(String taskId) {
		return historyServer.finishedTask(taskId);
	}*/
	/**
	 * 待办流程分页查询
	 * 
	 * @param response
	 * @param page     当前页数
	 * @param rows     每页显示页数
	 * @param s_name   流程名称
	 * @param userId   流程ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("taskPage")
	@ResponseBody
	public Object taskPage(String page, String rows, String s_name, String userId) {
		return historyServer.taskPage(page, rows, s_name, userId);

	}

	/**
	 * 查询当前流程图
	 * 
	 * @return
	 */
	@RequestMapping("showCurrentView")
	@ResponseBody
	public ModelAndView showCurrentView(String taskId) {
		return historyServer.showCurrentView(taskId);
	}
	/**
	 * 查询任务的操作历史流程图
	 * @param taskId
	 * @return
	 */
	@RequestMapping("showHistCurrentView")
	@ResponseBody
    public ModelAndView showHistCurrentView(String taskId) {
    	return historyServer.showHistCurrentView(taskId);
    }
	/**
	 * 查询历史批注
	 * 
	 * @param response
	 * @param taskId   流程ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listHistoryComent")
	@ResponseBody
	public Object listHistoryComment(String taskId) throws Exception {
		return historyServer.listHistoryComment(taskId);
	}

	/**
	 * 查詢流程正常走完的历史流程表 : act_hi_actinst
	 * 
	 * @param response
	 * @param rows
	 * @param page
	 * @param s_name
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("finishedList")
	@ResponseBody
	public Object finishedList(String rows, String page, String s_name, String groupId) throws Exception {
		return historyServer.finishedList(rows, page, s_name, groupId);
	}

	/**
	 * 根据任务id查询流程实例的具体执行过程
	 * 
	 * @param taskId
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listAction")
	@ResponseBody
	public Object listAction(String taskId) throws Exception {
		return historyServer.listAction(taskId);
	}
	
	@RequestMapping(value="findByFlowInfo",method=RequestMethod.POST)
	@ResponseBody
	public Object findByFlowInfo(String taskId) {
		Task task=taskService.createTaskQuery().taskId(taskId).singleResult(); 
		return applicationServer.finfByProcessId(task.getProcessInstanceId());
	}
	
	@RequestMapping(value="commentMsg" ,method=RequestMethod.POST)
	@ResponseBody
	public Object findByCommentMsg(String processInstanceId) {
		return historyServer.findByCommentMsg(processInstanceId);
	}
}
