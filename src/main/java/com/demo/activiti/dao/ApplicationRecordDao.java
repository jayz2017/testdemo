package com.demo.activiti.dao;

import com.demo.activiti.entity.CosApplicationRecord;

public interface ApplicationRecordDao {
	 /**
     * 插入操作记录日志
*/
	public int insertApplicationOperateRecord(CosApplicationRecord ApplicationRecord);

	public Object selectApplicationOperateList();


}
