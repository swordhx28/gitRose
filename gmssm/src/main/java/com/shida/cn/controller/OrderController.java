package com.shida.cn.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shida.cn.dao.model.Order;
import com.shida.cn.dao.model.OrderDetail;
import com.shida.cn.dao.model.Product;
import com.shida.cn.dao.model.User;
import com.shida.cn.service.IOrderDetailService;
import com.shida.cn.service.IOrderService;
import com.shida.cn.service.IProductService;

@Controller
@RequestMapping("")
public class OrderController {
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IOrderDetailService orderDetailService;
	@Autowired
	private IProductService productService;
	
	@RequestMapping("/buy")
	public String buy(String pid,HttpServletRequest request) {
		Product productById = productService.getProductById(Long.valueOf(pid));
		request.setAttribute("product", productById);
		return "buy";
	}
	
	@RequestMapping("/addbuy")
	public void addbuy(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		response.setContentType("text/plain;charset=utf-8");
		String username = request.getParameter("username");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String pid = request.getParameter("pid");
		String price = request.getParameter("price");
		String buyNum = request.getParameter("buyNum");
		System.out.println(username);
		User user = (User) session.getAttribute("resultUser");
		Order order = new Order();
		order.setAddress(address);
		order.setPhone(phone);
		order.setUserId(user.getId());
		int row = orderService.save(order);
		if(row > 0) {
			response.getWriter().print("保存订单成功！");
			System.out.println("保存订单成功！");
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBuyNum(Integer.valueOf(buyNum));
			orderDetail.setProductId(Long.valueOf(pid));
			orderDetail.setPrice(Float.valueOf(price));
			orderDetail.setOrderId(order.getId());
			int row2 = orderDetailService.save(orderDetail);
			if(row2 > 0) {
				System.out.println("保存订单详情成功！");
			}else {
				System.out.println("保存订单详情失败！");
			}
		}else {
			System.out.println("保存订单失败！");
		}
	}
}
