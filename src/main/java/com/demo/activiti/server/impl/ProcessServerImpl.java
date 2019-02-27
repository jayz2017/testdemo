package com.demo.activiti.server.impl;

import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.demo.activiti.server.ProcessService;

@Service("ProcessService")
public class ProcessServerImpl implements ProcessService {
	@Resource
	private RepositoryService repositoryService;

	@Override
	public boolean DeployServer(String WorkName,String deployFlowName) {
		boolean deployFlag = true;
		try {
			Deployment deployment = repositoryService.createDeployment().name(deployFlowName)
					.addClasspathResource("activiti/" + WorkName + ".bpmn").deploy();
			if (deployment == null) {
				deployFlag = false;
			}
		} catch (Exception e) {
			deployFlag = false;
		}
		return deployFlag;
	}

	public String addDeploy(HttpServletResponse response, MultipartFile deployFile) throws Exception {
		repositoryService.createDeployment().name(deployFile.getOriginalFilename())
				.addZipInputStream(new ZipInputStream(deployFile.getInputStream())).deploy();
		return null;
	}
/*

*ACT_RE_DEPLOYMENT 部署对象表
*act_re_procdef  流程定义表
*act_re_bytearray 资源文件表
*act_ge_property 主键生成策略表
*
*
*
*
*/
	@Override
	public boolean deleteProcess(String deployId) {
		boolean processFlag = true;
		try {
			/*//流程定义的key
			String processDefinitonKey="";
			List<ProcessDefinition>list = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitonKey).list();
			//部署ID
			for(ProcessDefinition li:list) {
				String deploymentId=li.getDeploymentId();
			}*/
			repositoryService.deleteDeployment(deployId, true);
		} catch (Exception e) {
			processFlag = false;
		}
		return processFlag;
	}

	@Override
	public boolean UpdateProcess(String WorkId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Deployment> selectProcessList(String name, int startPage, int maxResults) {
		List<Deployment> deployment = repositoryService.createDeploymentQuery().deploymentTenantId(name)
				.listPage(startPage, maxResults);
		return deployment;
	}

	
}
