package com.yueqian.auction.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yueqian.auction.pojo.AuctionUser;
import com.yueqian.auction.service.UserService;

@Controller
// @RequestMapping("/user")
public class UserController {

	@Autowired
	private DefaultKaptcha captchaProducer;
	@Autowired
	private UserService UserService;

	@GetMapping("/login")	
	public String login() {
		return "login";
	}
	
	@GetMapping("/toRegister")
	public String toRegister(@ModelAttribute("registerUser") AuctionUser registerUser) {
		return "register";
		
	}

	@RequestMapping("/doLogin")
	public String doLogin(String username,String userPassword , String valideCode ,Model model ,HttpSession session) {
		
		if (!session.getAttribute("vrifyCode").equals(valideCode)) {
			model.addAttribute("errorMsg","验证码不正确");
			return "login";
		}
		
		AuctionUser loginUser = UserService.login(username, userPassword);
		if (loginUser !=null) {
			session.setAttribute("user", loginUser);
			return "index";
		}else {
			model.addAttribute("errorMsg","账号或密码不正确");
			return "login";
		}
	}
	
	
	
	@RequestMapping("/register")
	public String register(Model model,@ModelAttribute("registerUser")@Validated AuctionUser auctionUser,BindingResult bindingResult) {
	
		if (bindingResult.hasErrors()) {
			
			List<FieldError> list = bindingResult.getFieldErrors();
			for (FieldError fieldError : list) {
				model.addAttribute(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return "register";
		}
		
		UserService.register(auctionUser);
		
		return "login";
		
	}
	
	
	/**
	 * 获取验证码
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws Exception
	 */
	@RequestMapping("/defaultKaptcha")
	public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws Exception {
		byte[] captchaChallengeAsJpeg = null;
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// 生产验证码字符串并保存到session中
			String createText = captchaProducer.createText();
			httpServletRequest.getSession().setAttribute("vrifyCode", createText);
			// 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
			BufferedImage challenge = captchaProducer.createImage(createText);
			ImageIO.write(challenge, "jpg", jpegOutputStream);
		} catch (IllegalArgumentException e) {
			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		httpServletResponse.setHeader("Cache-Control", "no-store");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);
		httpServletResponse.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
	}
}
