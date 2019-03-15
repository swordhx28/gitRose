package com.yueqian.auction.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yueqian.auction.pojo.Auction;
import com.yueqian.auction.pojo.AuctionCustom;
import com.yueqian.auction.pojo.AuctionRecord;
import com.yueqian.auction.pojo.AuctionUser;
import com.yueqian.auction.service.AuctionService;

@Controller
public class AuctionController {

	@Autowired
	private AuctionService auctionService;

	public static final int PAGE_SIZE = 6;
	
	@GetMapping("/toAddAuction")
	public String toAddAuction() {
		
		return "addAuction";
		
	}
	

	@RequestMapping("/queryAuctions")
	public ModelAndView queryAuctions(@ModelAttribute("condition") Auction auction,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {

		ModelAndView mv = new ModelAndView();

		// 设置分页区间
		PageHelper.startPage(pageNum, PAGE_SIZE);

		List<Auction> list = auctionService.findAuctions(auction);

		// 创建分页bean
		PageInfo page = new PageInfo(list);

		mv.addObject("auctionList", list);
		mv.addObject("page", page);
		mv.setViewName("index");
		return mv;
	}
	
	
	@RequestMapping("/toAuctionDetail/{auctionid}")
	public ModelAndView toAuctionDetail(@PathVariable int auctionid) {
		Auction auctionDetail = auctionService.findAuctionRecordList(auctionid);
		ModelAndView mv = new ModelAndView();
		mv.addObject("auctionDetail", auctionDetail);
		mv.setViewName("auctionDetail");
		return mv;
	}
	
	@RequestMapping("/publishAuctions")
	public String publishAuctions(Auction auction,MultipartFile pic) {
		try {
			if (pic.getSize() > 0) {
				String path = "d:/file";
				File targetFile = new File(path, pic.getOriginalFilename());
				pic.transferTo(targetFile);
				auction.setAuctionpic(pic.getOriginalFilename());
				auction.setAuctionpictype(pic.getContentType());
			}
			auctionService.addAuction(auction);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/queryAuctions";
	}

	@RequestMapping("/saveAuctionRecord")
	public String saveAuctionRecord(AuctionRecord record , HttpSession session)throws Exception {
		
		//获取登陆用户id
		AuctionUser user = (AuctionUser) session.getAttribute("user");
		record.setUserid(user.getUserid());
		
		//拍卖时间
		record.setAuctiontime(new Date());
		
		auctionService.addAuctionRecord(record);
		
		return "redirect:/toAuctionDetail/"+record.getAuctionid();
	}
	
	@RequestMapping("/toAuctionResult")
	public ModelAndView toAuctionResult() {
		
		List<AuctionCustom> endtimeList = auctionService.findAuctionEndtime();
		List<Auction> noendtimeList = auctionService.findAuctionNoendtime();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("endtimeList", endtimeList);
		mv.addObject("noendtimeList", noendtimeList);
		mv.setViewName("auctionResult");
		
		return mv;
		
	}
	
	
	@RequestMapping("/toUpdate/{auctionid}")
	public ModelAndView toUpdate(@PathVariable int auctionid) {
		Auction auction = auctionService.findAuctionById(auctionid);
		ModelAndView mv = new ModelAndView();
		mv.addObject("auction",auction);
		mv.setViewName("updateAuction");
		return mv;
	}
	
	@RequestMapping("/removeAuction/{auctionid}")
	public String removeAuction(@PathVariable int auctionid) {
		auctionService.removeAuction(auctionid);
		
		return "redirect:/queryAuctions";
	}
	
	
	@RequestMapping("/submitUpdateAuction")
	public String submitUpdateAuction(Auction  auction,MultipartFile pic) {
		try {
			if (pic.getSize() > 0) {
				String path = "d:/file";
				//删除旧的文件
				File oldFile = new File(path,auction.getAuctionpic());
				if (oldFile.exists()) {
					oldFile.delete();
				}
				File targetFile = new File(path, pic.getOriginalFilename());
				pic.transferTo(targetFile);
				auction.setAuctionpic(pic.getOriginalFilename());
				auction.setAuctionpictype(pic.getContentType());
			}
			auctionService.updateAuction(auction);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/queryAuctions";
		
	}
	
	
}
