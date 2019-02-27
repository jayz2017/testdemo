package com.demo.activiti.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.activiti.entity.Staff;
import com.demo.activiti.server.LoginServer;
import com.demo.activiti.util.ResponseUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("login")
public class LoginController {
    @Resource
	public   LoginServer loginServer;
    @RequestMapping("Login")
    public String userLogin(HttpServletResponse response,HttpServletRequest request) throws Exception{
    	Staff staff=new Staff();
    	staff.setStaff_name(request.getParameter("userName"));
    	Staff staff1=loginServer.findByUsername(staff);
		JSONObject result=new JSONObject();
		if(staff1==null){
			result.put("success", false);
			result.put("errorInfo", "登陆失败");
		}else{
			result.put("success", true);
			request.getSession().setAttribute("currentMemberShip", staff1);
		}
		ResponseUtil.write(response, result);
		return null;
	}
}
