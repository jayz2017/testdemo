package com.demo.activiti.server;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public interface HistoryServer {
	public Object listHistoryCommentWithProcessInstanceId(String processInstanceId);

	public Object taskPage(String page, String rows, String s_name, String userId);

	public ModelAndView showCurrentView(String taskId);

	public Object listHistoryComment(String taskId);

	public Object finishedList(String rows, String page, String s_name, String groupId);

	public Object listAction(String taskId);

	public List<Map<String,Object>>  findMyPersonalTask(String assigneeRoleName);

	public Object finishedTask(String taskId);
	
	public Object findByCommentMsg(String processInstanceId);

	public ModelAndView showHistCurrentView(String taskId);
}
