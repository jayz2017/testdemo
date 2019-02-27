package com.demo.activiti.server;

import javax.annotation.Resource;

@Resource
public interface ListenerService {
	/**
	 * 会话监听开始
	 */
	void taskStart();

	/**
	 * 会话监听结束
	 */
	void endEnd();

	/**
	 * 流程 启动监听
	 */
	void Start();

	/**
	 * 流程结束监听
	 */
	void End();

	/**
	 * 会话执行监听
	 */
	void taskExecute();
}
