package com.demo.activiti.server.impl;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 任务监听器
 * 
 * @author 陈浩
 *
 */
public class ManagerListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
        System.out.println("当前："+delegateTask.getAssignee()+"在处理任务");
		  
	}

}
