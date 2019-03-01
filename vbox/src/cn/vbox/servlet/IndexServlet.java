package cn.vbox.servlet;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.vbox.dao.DBUtil;
import cn.vbox.entity.Tadmin;
import cn.vbox.entity.Tarticle;
import cn.vbox.entity.Tmenu;
import cn.vbox.server.IData;
import cn.vbox.server.TrackerServer;
public class IndexServlet {
	private static TrackerServer trackerServer=TrackerServer.startup("localhost", 9999);
	DBUtil dbUtil=new DBUtil();
	protected void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//查找所有pid=0的Tmemu对象
		ArrayList<Tmenu> tmenubox = (ArrayList<Tmenu>)dbUtil.find(Tmenu.class, "pid", 0);
		req.setAttribute("menubox", tmenubox);
		req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
	}
	protected void time(HttpServletResponse resp) throws ServletException, IOException {
		String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		resp.getWriter().print(time);
	}
	public void upload(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		String message = "fail";
		try {
			// 创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 判断是否是文件提交表单
			if (ServletFileUpload.isMultipartContent(req)) {
				// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
				List<FileItem> list = upload.parseRequest(req);
				for (FileItem item : list) {
					// 如果是二进制文件
					if (!item.isFormField()) {
						InputStream in = item.getInputStream();
						String savepath = trackerServer.upload(in, "jpg");
						message = "{\"data\":\""+savepath+"\"}"; //以json格式返回图片链接地址
					}
				}
			}
		} catch (Exception e) {
			message = "fail";
			e.printStackTrace();
		}
		resp.getWriter().print(message);
	}
	public void image( HttpServletRequest req,HttpServletResponse resp){
		try {
			String url=req.getParameter("filename");
			IData data = trackerServer.receive(url);
			resp.getOutputStream().write(data.getBuf(),0,data.getLen());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void delMenu(int id,String level){
		if(level.equals("child")){
			//删除目录的时候连同文章一起删除
			int aid = ((Tmenu)dbUtil.findById(Tmenu.class, id)).getFk_aid();
			dbUtil.del(Tmenu.class, id);
			dbUtil.del(Tarticle.class, aid);
		}else if (level.equals("parent")) {
			Tmenu menu = (Tmenu)dbUtil.findById(Tmenu.class, id);
			//查找所有子菜单并删除子菜单和关联的文章
			ArrayList<Tmenu> menubox =(ArrayList<Tmenu>) dbUtil.find(Tmenu.class, "pid", menu.getId());
			for (Tmenu m : menubox) {
				//delete from Tmenu where id in (1,3,2);
				dbUtil.del(Tarticle.class, m.getFk_aid());
				dbUtil.del(Tmenu.class, m.getId());
			}
			//把自己删除
			dbUtil.del(Tmenu.class, menu.getId());
		}
	
	}
	public void findMenu(int pid,HttpServletResponse resp){
		try {
			//子目录的排列顺序从posid从小到大
			ArrayList<Tmenu> tmenubox = (ArrayList<Tmenu>)dbUtil.find(Tmenu.class, "pid", pid,"posid","asc");
			StringBuilder sb=new StringBuilder();
			for (Tmenu m : tmenubox) {
				sb.append("id="+m.getId()+",").append("title="+m.getTitle()+",").append("pid="+m.getPid()+"|");
			}
			//将数据以UTF-8的编码输出
			resp.setCharacterEncoding("UTF-8");
			//去掉最后一个|
			resp.getWriter().print(sb.toString().substring(0,sb.length()-1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void menu(Tmenu menu,HttpServletResponse resp){
		try {
			if(menu.getId()!=0){
				dbUtil.update(menu);
			}else {
				dbUtil.save(menu);
			}
			resp.getWriter().print(menu.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void alterPosid(int curid,int previd){
		Tmenu curMenu=(Tmenu) dbUtil.findById(Tmenu.class, curid);
		Tmenu preMenu=(Tmenu) dbUtil.findById(Tmenu.class, previd);
		int curPosId=curMenu.getPosid();
		int prePosId=preMenu.getPosid();
		curMenu.setPosid(prePosId);
		preMenu.setPosid(curPosId);
		dbUtil.update(preMenu);
		dbUtil.update(curMenu);
	}
	public void login(String username,String password,HttpServletRequest req,HttpServletResponse resp){
		try {
			//唯物论：辩证法(寻迹法-->思外揣内-->归纳总结-->逻辑演绎-->定理)
			//唯心论：觉悟  顿悟 (禅修-->放弃物欲 格物)
			ArrayList<?> box = dbUtil.find(Tadmin.class, "username", username);
			if(!box.isEmpty()){ //对象不为null,但是判断里面是否有值
				Tadmin admin=(Tadmin) box.get(0);
				if(admin.getPassword().equals(password)){
					req.getSession().setAttribute("admin", admin);
					Cookie uCookie = new Cookie("username", admin.getUsername());
					uCookie.setPath("/");	//设置只有根目录能访问cookie
					uCookie.setMaxAge(600);
					Cookie pCookie = new Cookie("password", admin.getPassword());
					pCookie.setPath("/");
					pCookie.setMaxAge(600);
					resp.addCookie(uCookie);
					resp.addCookie(pCookie);
				}
			}
			resp.sendRedirect("/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//当点击目录的时候,获取该目录的文章内容
	public void getData(int id,HttpServletResponse resp){
		try {
			resp.setCharacterEncoding("UTF-8");
			Tmenu menu = (Tmenu)dbUtil.findById(Tmenu.class, id);
			//获取文章的数据
			int aid = menu.getFk_aid();
			Tarticle article = (Tarticle)dbUtil.findById(Tarticle.class, aid);
			if(article==null){
				article=new Tarticle();
				article.setContent("<h1>请输入……</h1>");
				dbUtil.save(article);
				menu.setFk_aid(article.getId());
				dbUtil.update(menu);
			}
			resp.getWriter().print(article.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//按ctrl+s的时候保存文章内容(一个目录一个文章)
	public void saveData(int id,String content){
		Tmenu menu = (Tmenu)dbUtil.findById(Tmenu.class, id);
		int aid = menu.getFk_aid();
		Tarticle article = (Tarticle)dbUtil.findById(Tarticle.class, aid);
		article.setContent(content);
		dbUtil.update(article);
	}
	//让session和cookie失效
	public void logout(HttpServletRequest req,HttpServletResponse resp){
		try {
			HttpSession session = req.getSession();
			Tadmin admin = (Tadmin)session.getAttribute("admin");
			Cookie uCookie = new Cookie("username", admin.getUsername());
			uCookie.setPath("/");	//设置只有根目录能访问cookie
			uCookie.setMaxAge(0);//让客户端cookie失效
			Cookie pCookie = new Cookie("password", admin.getPassword());
			pCookie.setPath("/");
			pCookie.setMaxAge(0);//让客户端cookie失效
			resp.addCookie(uCookie);
			resp.addCookie(pCookie);
			session.invalidate();//摧毁当前session
			resp.sendRedirect("/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}