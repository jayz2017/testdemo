package com.demo.activiti.server.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.GraphicInfo;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.demo.activiti.entity.PageInfo;
import com.demo.activiti.server.HistoryServer;
import com.demo.activiti.util.ObjectUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("HistoryServer")
public class HistoryServerImpl implements HistoryServer {
	@Resource
	private TaskService taskService;

	@Resource
	private RepositoryService repositoryService;

	@Resource
	private RuntimeService runtimeService;

	@Resource
	private HistoryService historyService;

	/**
	 * 鏌ヨ鍘嗗彶娴佺▼鎵规敞
	 * 
	 * @param response
	 * @param processInstanceId 娴佺▼ID
	 * @return
	 * @throws Exception
	 */
	public Object listHistoryCommentWithProcessInstanceId(String processInstanceId) {
		if (processInstanceId == null) {
			return null;
		}
		List<Comment> commentList = taskService.getProcessInstanceComments(processInstanceId);
		// 鏀瑰彉椤哄簭锛屾寜鍘熼『搴忕殑鍙嶅悜椤哄簭杩斿洖list
		Collections.reverse(commentList); // 闆嗗悎鍏冪礌鍙嶈浆
		return commentList;
	}

	/**
	 * 
	 * @param assigneeName 澶勭悊浜虹殑瑙掕壊鐨勫悕绉�
	 */
	public List<Map<String, Object>> findMyPersonalTask(String assigneeRoleName) {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = null;
		List<Task> list1 = taskService.createTaskQuery().taskCandidateGroup(assigneeRoleName).list();
		if (list1.size() > 0 && list1 != null) {
			for (Task li : list1) {
				map = new HashMap<String, Object>();
				map.put("id", li.getId());
				map.put("name", li.getName());
				map.put("createTime", li.getCreateTime());
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 寰呭姙娴佺▼鍒嗛〉鏌ヨ
	 * 
	 * @param response
	 * @param page     褰撳墠椤垫暟
	 * @param rows     姣忛〉鏄剧ず椤垫暟
	 * @param s_name   娴佺▼鍚嶇О
	 * @param userId   娴佺▼ID
	 * @return
	 * @throws Exception
	 */
	public Object taskPage(String page, String rows, String s_name, String userId) {
		if (s_name == null) {
			s_name = "";
		}
		PageInfo pageInfo = new PageInfo();
		Integer pageSize = Integer.parseInt(rows);
		pageInfo.setPageSize(pageSize);
		if (page == null || page.equals("")) {
			page = "1";
		}
		pageInfo.setPageIndex((Integer.parseInt(page) - 1) * pageInfo.getPageSize());
		// 鑾峰彇鎬昏褰曟暟
		System.out.println("鐢ㄦ埛ID锛�" + userId + "\n" + "鍚嶇О:" + s_name);
		long total = taskService.createTaskQuery().taskCandidateGroup(userId).taskNameLike("%" + s_name + "%").count(); // 鑾峰彇鎬昏褰曟暟
		// 鏈夋兂娉曠殑璇濓紝鍙互鍘绘暟鎹簱瑙傚療 ACT_RU_TASK 鐨勫彉鍖�
		List<Task> taskList = taskService.createTaskQuery()
				// 鏍规嵁鐢ㄦ埛id鏌ヨ
				.taskCandidateGroup(userId)
				// 鏍规嵁浠诲姟鍚嶇О鏌ヨ
				.taskNameLike("%" + s_name + "%")
				// 杩斿洖甯﹀垎椤电殑缁撴灉闆嗗悎
				.listPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
		// 杩欓噷闇�瑕佷娇鐢ㄤ竴涓伐鍏风被鏉ヨ浆鎹竴涓嬩富瑕佹槸杞垚JSON鏍煎紡
		List<Map<String, Object>> MyTaskList = new ArrayList<Map<String, Object>>();
		for (Task t : taskList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", t.getId());
			map.put("name", t.getName());
			map.put("createname", t.getCreateTime());
			MyTaskList.add(map);
		}
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(MyTaskList);
		result.put("rows", jsonArray);
		result.put("total", total);
		return result;
	}

	/**
	 * 获取正在执行任务的当前流程图
	 * 
	 * @return
	 */
	public ModelAndView showCurrentView(String taskId) {
		ModelAndView mav = new ModelAndView();

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processDefinitionId = task.getProcessDefinitionId();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		mav.addObject("deploymentId", processDefinition.getDeploymentId());
		mav.addObject("diagramResourceName", processDefinition.getDiagramResourceName());

		BpmnModel processDefinitionEntity = repositoryService.getBpmnModel(processDefinitionId);
				//.getProcessDefinition(processDefinitionId);
		String processInstanceId = task.getProcessInstanceId();
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
				.singleResult();
		GraphicInfo info=processDefinitionEntity.getLocationMap().get(pi.getActivityId());
		//ActivityImpl activityImpl = processDefinitionEntity.get(pi.getActivityId());
		mav.addObject("x", info.getX());
		mav.addObject("y", info.getY());
		mav.addObject("width", info.getWidth());
		mav.addObject("height", info.getHeight());
		mav.setViewName("page/currentView");
		return mav;
	}

	/**
	 * 鏌ヨ鍘嗗彶鎵规敞
	 * 
	 * @param response
	 * @param taskId   娴佺▼ID
	 * @return
	 * @throws Exception
	 */
	public Object listHistoryComment(String taskId) {
		if (taskId == null) {
			taskId = "";
		}
		HistoricTaskInstance hti = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		List<Comment> commentList = null;
		if (hti != null) {
			commentList = taskService.getProcessInstanceComments(hti.getProcessInstanceId());
			// 闆嗗悎鍏冪礌鍙嶈浆
			Collections.reverse(commentList);
		}
		return commentList;
	}

	/**
	 * 鏌ヨ娴佺▼姝ｅ父璧板畬鐨勫巻鍙叉祦绋嬭〃 : act_hi_actinst
	 * 
	 * @param response
	 * @param rows
	 * @param page
	 * @param s_name
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	public Object finishedList(String rows, String page, String s_name, String groupId) {
		// 涓轰粈涔堣杩欐牱鍛紵鍥犱负绋嬪簭棣栨杩愯杩涘叆鍚庡彴鏃讹紝
		// s_name蹇呭畾鏄瓑浜巒ull鐨勶紝濡傛灉鐩存帴杩欐牱濉啓杩涙煡璇㈣鍙ヤ腑灏变細鍑虹幇 % null %杩欐牱灏变細瀵艰嚧鏌ヨ缁撴灉鏈夎
		if (s_name == null) {
			s_name = "";
		}
		PageInfo pageInfo = new PageInfo();
		Integer pageSize = Integer.parseInt(rows);
		pageInfo.setPageSize(pageSize);
		if (page == null || page.equals("")) {
			page = "1";
		}
		pageInfo.setPageIndex((Integer.parseInt(page) - 1) * pageSize);
		/*
		 * select distinct RES.* from ACT_HI_TASKINST RES inner join ACT_HI_IDENTITYLINK
		 * HI on HI.TASK_ID_ = RES.ID_ WHERE RES.NAME_ like ? and RES.ASSIGNEE_ is null
		 * and HI.TYPE_ = 'candidate' and ( HI.GROUP_ID_ IN ( ? ) ) order by RES.ID_ asc
		 * LIMIT ? OFFSET ?
		 */
		List<HistoricTaskInstance> histList = historyService.createHistoricTaskInstanceQuery()
				// .taskCandidateUser(groupId)
				.taskCandidateGroup(groupId)// 鏍规嵁瑙掕壊鍚嶇О鏌ヨ
				.taskNameLike("%" + s_name + "%").listPage(pageInfo.getPageIndex(), pageInfo.getPageSize());

		long histCount = historyService.createHistoricTaskInstanceQuery().taskCandidateGroup(groupId)
				.taskNameLike("%" + s_name + "%").count();
		List<Map<String, Object>> MyTaskList = new ArrayList<Map<String, Object>>();
		for (HistoricTaskInstance t : histList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", t.getId());
			map.put("name", t.getName());
			map.put("createname", t.getCreateTime());
			map.put("endTime", t.getEndTime());
			MyTaskList.add(map);
		}
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(MyTaskList);
		result.put("rows", jsonArray);
		result.put("histCount", histCount);
		return result;
	}

	/**
	 * 鏍规嵁浠诲姟id鏌ヨ娴佺▼瀹炰緥鐨勫叿浣撴墽琛岃繃绋�
	 * 
	 * @param taskId
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object listAction(String taskId) {
		HistoricTaskInstance hti = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		String processInstanceId = hti.getProcessInstanceId(); // 鑾峰彇娴佺▼瀹炰緥id
		List<HistoricActivityInstance> haiList = historyService.createHistoricActivityInstanceQuery()
				.processInstanceId(processInstanceId).list();
		return haiList;
	}

	@Override
	public Object finishedTask(String taskId) {
		taskService.complete(taskId);
		return null;
	}

	/**
	 * 
	 */
	public Object findByCommentMsg(String processInstanceId) {
		List<Map<String, Object>> listInfo = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		// historyService.createHistoricDetailQuery().executionId(executionId).list();
		List<Comment> list = taskService.getProcessInstanceComments(processInstanceId, "comment");// comment琛ㄧず鏌ヨ璇勮绫诲瀷鐨勬暟鎹�
		if (list != null && list.size() > 0) {
			for (Comment li : list) {
				map = new HashMap<String, Object>();
				map.put("approval", li.getUserId());
				map.put("message", li.getFullMessage());
				listInfo.add(map);
			}
		}
		return listInfo;
	}

	
	/**
	 * 查询历史操作流程图
	 */
	@Override
	public ModelAndView showHistCurrentView(String taskId) {
		ModelAndView mav = new ModelAndView();
		String processDefinitionId = null;
		String processInstanceId = null;
		GraphicInfo activityImpl =null;
		// 创建一个历史任务查询
		HistoricTaskInstance singleResultDetail = historyService.createHistoricTaskInstanceQuery().taskId(taskId)
				.singleResult();
		if (!ObjectUtils.isNullOrEmpty(singleResultDetail)) {
			
			 HistoricTaskInstance historicTaskInstance =
			historyService.createHistoricTaskInstanceQuery()
			  .processInstanceId(singleResultDetail.getProcessInstanceId()).
			orderByTaskCreateTime().desc().list() .get(0);
			 
			// if (!ObjectUtils.isNullOrEmpty(historicTaskInstance)) {
			// 去查询正在执行的任务存在不，不存在则去查历史流程步骤表
			List<Task> list = taskService.createTaskQuery().processInstanceId(singleResultDetail.getProcessInstanceId())
					.list();
			if (list != null && list.size() > 0) {
				for (Task task : list) {
					processDefinitionId = task.getProcessDefinitionId();
					processInstanceId = task.getProcessInstanceId();
					activityImpl = Node(mav, processDefinitionId, processInstanceId,null);
					
				}
			} else {
				HistoricActivityInstance activityInstance = historyService.createHistoricActivityInstanceQuery()
						.processInstanceId(singleResultDetail.getProcessInstanceId())
						.orderByHistoricActivityInstanceStartTime().desc().list().get(0);
				if (!ObjectUtils.isNullOrEmpty(activityInstance)) {
					processDefinitionId = activityInstance.getProcessDefinitionId();
					processInstanceId = activityInstance.getProcessInstanceId();
					activityImpl =Node(mav, processDefinitionId, processInstanceId,activityInstance.getActivityId()) ;
					
				}
			}
			mav.addObject("x", activityImpl.getX());
			mav.addObject("y", activityImpl.getY());
			mav.addObject("width", activityImpl.getWidth());
			mav.addObject("height", activityImpl.getHeight());
			mav.setViewName("page/currentView");
		}
		return mav;
	}

	
	public GraphicInfo Node(ModelAndView mav, String processDefinitionId, String processInstanceId,String activityId) {
		String act_id=null;
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		mav.addObject("deploymentId", processDefinition.getDeploymentId());
		mav.addObject("diagramResourceName", processDefinition.getDiagramResourceName());
		BpmnModel processDefinitionEntity = repositoryService.getBpmnModel(processDefinitionId);
				//.getProcessDefinition(processDefinitionId);
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
				.singleResult();
		
		if(!ObjectUtils.isNullOrEmpty(pi)) {
			act_id=pi.getActivityId();
		}else {
			act_id=activityId;
		}
	   //return processDefinitionEntity.findActivity(act_id);
		return processDefinitionEntity.getLocationMap().get(act_id);
	}


}
