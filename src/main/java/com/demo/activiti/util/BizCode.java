package com.demo.activiti.util;

/**
 * @author chenhao
 * @version 1.0
 */
public interface BizCode {
    /**
             * 编码格式
     */
	String GLOBAL_ENCODING = "UTF-8";
	public static interface Status {

		/**
		 * 审核中
		 */
		int REVIEWING = -4;

		/**
		 * 删除
		 */
		int DELETE = -3;

		/**
		 * 被拒绝
		 */
		int REFUSED = -2;

		/**
		 * 待审批
		 */
		int APPROVING = -1;
		/**
		 * 提交
		 */
		int REFER = 4;

		/**
		 * 完成
		 */
		int FINISH = 9;
		/**
		 * 驳回
		 */
		int REJECT = 5;
	}
	
	public static interface Task {
		/**
		 * 优先级别 10 一般
		 */
		int COMMONLY = 10;
		/**
		 * 优先级别 11重要
		 */
		int IMPORTANT = 11;
		/**
		 * 优先级别 12非常重要
		 */
		int VERYIMPORTANT = 12;
		/**
		 * 优先级别 13 里程碑事件
		 */
		int MILESTONE = 13;
	}
}
