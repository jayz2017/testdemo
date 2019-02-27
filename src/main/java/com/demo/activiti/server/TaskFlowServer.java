package com.demo.activiti.server;

import net.sf.json.JSONObject;

public interface TaskFlowServer {

	boolean StartTask(String workFlowId,String applicationId);


	boolean UpdateTaskType(JSONObject object);

    boolean StartTask1(String workFlowName,String applicationId);
}
