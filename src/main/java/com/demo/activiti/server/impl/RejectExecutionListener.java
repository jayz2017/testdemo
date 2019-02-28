package com.demo.activiti.server.impl;

import java.util.List;

import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.history.HistoricTaskInstance;


public class RejectExecutionListener implements ExecutionListener {
	private static final long serialVersionUID = 1L;
	

	public void notify(DelegateExecution execution) {
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		String processInstId = execution.getProcessInstanceId();
		SequenceFlow flowElementInfo= (SequenceFlow)execution.getCurrentFlowElement();
		String targetRef=flowElementInfo.getTargetRef();
		List<HistoricTaskInstance> list = engine.getHistoryService().createHistoricTaskInstanceQuery()
				.orderByTaskCreateTime().desc().processInstanceId(processInstId).taskDefinitionKey(targetRef).list();
		if (list != null) {
			String user = list.get(0).getAssignee(); 
			execution.setVariable("Reject", user);
		}
	}
}
