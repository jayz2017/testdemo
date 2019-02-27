package com.demo.activiti.server.impl;

import org.springframework.stereotype.Service;

import com.demo.activiti.server.ListenerService;

@Service("ListenerService")
public class ListenerServiceImpl implements ListenerService {
   
	@Override
	public void taskStart() {
		System.out.println("activi:会话启动监听---------------开始");
	}

	@Override
	public void endEnd() {
		System.out.println("activi:会话启动监听--------------结束");
	}

	@Override
	public void Start() {
		System.out.println("activi:流程启动监听---------------开始");

	}

	@Override
	public void End() {
		System.out.println("activi:流程启动监听---------------结束");
	}

	@Override
	public void taskExecute() {
		System.out.println("activi:任务执行监听---------------开始");

	}
  
}
