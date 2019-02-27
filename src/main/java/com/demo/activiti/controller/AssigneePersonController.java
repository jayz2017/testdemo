package com.demo.activiti.controller;

import javax.annotation.Resource;

import org.activiti.engine.TaskService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("Assignee")
public class AssigneePersonController {
	@Resource
	private TaskService taskService;
}
