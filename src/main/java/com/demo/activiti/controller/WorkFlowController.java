package com.demo.activiti.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.activiti.server.FlowServer;
import com.demo.activiti.server.ProcessService;
import com.demo.activiti.util.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("workflow")
public class WorkFlowController {
	@Resource
	public FlowServer flowServer;
	@Resource
	public ProcessService processService;
	@Resource
	private RepositoryService repositoryService;
    /**
             * 鑾峰彇娴佺▼瀹氫箟鐨勪俊鎭垪琛�
     * @param request
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "findAll", method = RequestMethod.POST)
	@ResponseBody
	public Object findAll(HttpServletRequest request) throws Exception {
		String productType = request.getParameter("productType");
		Object object = flowServer.findAll(productType);
		return object;
	}
	/**
	 * 鏌ユ壘鍗曚竴鐨勬祦绋嬩俊鎭�
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "findOne", method = RequestMethod.POST)
	@ResponseBody
	public Object findOne(HttpServletRequest request) throws Exception {
		String workId = request.getParameter("workId");
		Object object = flowServer.findOne(workId);
		return object;
	}
	/**
	 *閮ㄧ讲娴佺▼
	 * @return
	 */
	@RequestMapping(value ="deploy",method=RequestMethod.POST)
	@ResponseBody
	public boolean deployFlow(@RequestParam String workname,@RequestParam String deployFlowName) {
		boolean deployFlag=processService.DeployServer(workname,deployFlowName);
		return deployFlag;
	}
	/**
	 * 鍒犻櫎娴佺▼瀹氫箟
	 * @param depolyMentId
	 * @return
	 */
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public boolean deleteProcessDefinition(String depolyMentId) {
	return	processService.deleteProcess(depolyMentId);
		
	}
	/**
	 * 鏌ョ湅娴佺▼鍥�
	 * @param deploymentId  娴佺▼ID
	 * @param diagramResourceName
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showView")
	public String showView(String deploymentId,String diagramResourceName,HttpServletResponse response)throws Exception{
		InputStream inputStream=repositoryService.getResourceAsStream(deploymentId, diagramResourceName);
		OutputStream out=response.getOutputStream();
		for(int b=-1;(b=inputStream.read())!=-1;){
			out.write(b);
		}
		out.close();
		inputStream.close();
		return null;
	}
	/**
	 * 娴佺▼瀹氫箟鍒嗛〉鏌ヨ
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/processDefinitionPage")
	public Object processDefinitionPage(String rows,String s_name,String page,HttpServletResponse response) throws Exception{
		if(s_name==null){
			s_name="";
		}
		if(page==null||page.equals("")){
			page="1";
		}
		long count=repositoryService.createProcessDefinitionQuery()
				.processDefinitionNameLike("%"+s_name+"%")
				.count();
		List<ProcessDefinition> processDefinitionList=repositoryService.createProcessDefinitionQuery()
				.orderByDeploymentId().desc()
				.processDefinitionNameLike("%"+s_name+"%")
				.listPage((Integer.parseInt(page) - 1)
						* Integer.parseInt(rows), Integer.parseInt(rows));
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"identityLinks","processDefinition"});
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(processDefinitionList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", count);
		ResponseUtil.write(response, result);
		return null;
	}
}
