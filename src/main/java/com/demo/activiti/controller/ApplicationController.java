package com.demo.activiti.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.demo.activiti.entity.CosApplication;
import com.demo.activiti.server.ApplicationServer;

@Controller
@RequestMapping("Application")
public class ApplicationController {
	@Resource
	public ApplicationServer applicationServer;

	@ResponseBody
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public boolean Insert(@RequestBody JSONArray Application) {
		return applicationServer.insertOrderInfo(Application);
	}
	@ResponseBody
	@RequestMapping(value = "insert1", method = RequestMethod.POST)
	public boolean Insert1(CosApplication cosApplication) {
		return applicationServer.insertOrderInfo1(cosApplication);
	}
	@ResponseBody
	@RequestMapping(value = "selectOne", method = RequestMethod.POST)
	public Object selectOne(String applicationId) {
		return applicationServer.selectApplicationList(applicationId);
	}

	@ResponseBody
	@RequestMapping(value = "selectAll", method = RequestMethod.POST)
	public Object selectAll() {
		return applicationServer.selectApplicationList(null);
	}
}
