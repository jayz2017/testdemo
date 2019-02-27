package com.demo.activiti.server.impl;

import java.util.Collection;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.EngineServices;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.history.HistoricTaskInstance;

/**
 * 任意节点的驳回
 * 
 * @author jekk
 *
 */
public class CrossClassRejectListener implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		List<HistoricTaskInstance> list=null;
		String sequenceFlowId=execution.getId();
		EngineServices services = execution.getEngineServices();
		RepositoryService repositoryService2 = services.getRepositoryService();
		String processDefinitionId = execution.getProcessDefinitionId();
		// 获取bpmnModel对象
		BpmnModel bpmnModel = repositoryService2.getBpmnModel(processDefinitionId);
		// 因为我们这里只定义了一个Process 所以获取集合中的第一个即可
		org.activiti.bpmn.model.Process process = bpmnModel.getProcesses().get(0);
		// 获取所有的FlowElement信息
		Collection<FlowElement> flowElements = process.getFlowElements();
		for (FlowElement flowElement : flowElements) {
			// 如果是任务节点
			if (flowElement instanceof UserTask) {
				UserTask userTask = (UserTask) flowElement;
				// 获取入线信息
				List<SequenceFlow> incomingFlows = userTask.getIncomingFlows();
				for (SequenceFlow sequenceFlow : incomingFlows) {
					 if(sequenceFlow.getId().equals(sequenceFlowId)) {
						String targetRef= sequenceFlow.getTargetRef();
						 list=services.getHistoryService()
						.createHistoricTaskInstanceQuery()
						.processDefinitionId(processDefinitionId)
						.taskDefinitionKey(targetRef)//根据定义的节点ID名称查询，也就hi是targetRef指向的资源节点名称
						//.singleResult()
						.list();
						break;
					 }
					System.out.println(sequenceFlow.getId() + "-" + sequenceFlow.getConditionExpression() + "--"
							+ sequenceFlow.getDocumentation() + "-" + sequenceFlow.getSourceRef() + "--"
							+ sequenceFlow.getTargetRef() + "-");
				}
			}
		}
		//遍历task历史的节点,设置针对人的任务信息
		if(list.size()>0 && list!=null) {
			String user = list.get(0).getAssignee(); // 获取最新的一个责任人信息回退给他
			//String role = list.get(0).llll
			execution.setVariable("Reject", user);
		}
	}

}
