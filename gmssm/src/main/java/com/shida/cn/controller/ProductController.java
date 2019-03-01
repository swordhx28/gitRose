package com.shida.cn.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shida.cn.dao.model.Category;
import com.shida.cn.dao.model.Image;
import com.shida.cn.dao.model.Product;
import com.shida.cn.service.ICategoryService;
import com.shida.cn.service.IImageService;
import com.shida.cn.service.IProductService;

@Controller
@RequestMapping("")
public class ProductController {
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IProductService productService;
	@Autowired
	private IImageService imageService;
	
	@RequestMapping("/addProduct")
	public String addProduct(HttpServletRequest request) {
		List<Category> categoryOneName = categoryService.getCategoryOneName();
		List<Category> categoryTweName = new ArrayList<Category>();
		Map<Category,List<Category>> cateMap = new HashMap<Category,List<Category>>();
		for (Category category : categoryOneName) {
			Long id = category.getId();
			categoryTweName = categoryService.getCategoryTweName(id);
			cateMap.put(category, categoryTweName);
		}
		request.setAttribute("categoryName", cateMap);
		return "back/add_product";
	}
	
	@RequestMapping("/insertProduct")
	public String insertProduct(String name,Float price,Float freight,Date expireTime,Integer stockNum,String sellAddress,Long categoryId,HttpSession session,HttpServletRequest request) throws Exception {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setFreight(freight);
		product.setExpireTime(expireTime);
		product.setStockNum(stockNum);
		product.setSellAddress(sellAddress);
		product.setCategoryId(categoryId);
		int row = productService.save(product);
		if(row > 0) {
			System.out.println(row);
			System.out.println("保存产品成功！");
		}else {
			System.out.println("保存产品失败！");
		}
		ServletContext servletContext = session.getServletContext();
		String realPath = servletContext.getRealPath("upload");
		if(request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> files = multiRequest.getFiles("images");
			for(MultipartFile file : files) {
				String fileName = file.getOriginalFilename();
				String uuid = UUID.randomUUID().toString();
				String extendName = fileName.substring(fileName.lastIndexOf("."));
				String onlyName = uuid + extendName;
				System.out.println(onlyName);
				file.transferTo(new File(realPath,onlyName));
				Image image = new Image();
				image.setProductId(product.getId());
				image.setUrl(onlyName);
				int row2 = imageService.save(image);
				if(row2 > 0) {
					System.out.println("保存图片成功！");
				} else {
					System.out.println("保存图片失败！");
				}
			}
		}
		return "back/index";
	}
	
	@RequestMapping("/info")
	public String info(String id,HttpServletRequest request) {
		Product productById = productService.getProductById(Long.valueOf(id));
		Category categoryById = categoryService.getCategoryById(productById.getCategoryId());
		request.setAttribute("product", productById);
		request.setAttribute("categoryName", categoryById.getName());
		return "info";
	}
	
}
