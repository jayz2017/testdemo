package com.demo.activiti.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.activiti.server.ActReDeploymentServer;

/**
 * 获取部署流程的部署信息
 * 
 * @author jekk
 *
 */
@Controller
@RequestMapping("actReDeployment")
public class ActReDeploymentController {
	@Resource
	public ActReDeploymentServer actReDeploymentServer;

	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public Object findAll() {
		return actReDeploymentServer.findAll();
	}

}
