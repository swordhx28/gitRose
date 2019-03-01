package com.shida.cn.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.shida.cn.dao.model.User;
import com.shida.cn.service.IUserService;

@Controller
@RequestMapping("/userController")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/showUser")
	public String showUser(String id,HttpServletRequest request) {
		User user = userService.getUserById(Long.parseLong(id));
		request.setAttribute("user", user);
		return "showUser";
	}
	
	@RequestMapping("/login")
	public String login(String loginName,String pass,HttpSession session) {
		User u = new User();
		u.setLoginName(loginName);
		u.setPass(pass);
		User resultUser = userService.login(u);
		session.setAttribute("resultUser", resultUser);
		return "login_success";
	}
	
	@RequestMapping("/register")
	public String register(User user,@RequestParam("image")CommonsMultipartFile file,HttpSession session) {
		String serverPath = session.getServletContext().getRealPath("upload");
		String fileName = file.getOriginalFilename();
		String uuid = UUID.randomUUID().toString();
		String extendsName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String onlyName = uuid + extendsName;
		user.setUserImg(onlyName);
		try {
			file.transferTo(new File(serverPath,onlyName));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String nyear = user.getNyear();
		String nmonth = user.getNmonth();
		String nday = user.getNday();
		String bornDay = nyear+"-"+nmonth+"-"+nday;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(bornDay);
			user.setBornDay(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int row = userService.save(user);
		if(row > 0) {
			session.setAttribute("resultUser", user);
			return "register_success";
		}else {
			return "register";
		}
	}
}
