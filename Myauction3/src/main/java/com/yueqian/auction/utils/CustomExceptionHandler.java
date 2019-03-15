package com.yueqian.auction.utils;

import static org.hamcrest.CoreMatchers.instanceOf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
@Component
public class CustomExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		AuctionpriceException ex =null;		
		if (exception instanceof AuctionpriceException) {
			ex= (AuctionpriceException) exception;
		}else {
			ex = new AuctionpriceException("未知异常");
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", ex.getMassage());
 		mv.setViewName("error");
		
		return mv;
	}

}
