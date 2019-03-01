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
		//��������pid=0��Tmemu����
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
			// ����һ���ļ��ϴ�������
			ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
			// ����ϴ��ļ�������������
			upload.setHeaderEncoding("UTF-8");
			// �ж��Ƿ����ļ��ύ��
			if (ServletFileUpload.isMultipartContent(req)) {
				// 4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
				List<FileItem> list = upload.parseRequest(req);
				for (FileItem item : list) {
					// ����Ƕ������ļ�
					if (!item.isFormField()) {
						InputStream in = item.getInputStream();
						String savepath = trackerServer.upload(in, "jpg");
						message = "{\"data\":\""+savepath+"\"}"; //��json��ʽ����ͼƬ���ӵ�ַ
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
			//ɾ��Ŀ¼��ʱ����ͬ����һ��ɾ��
			int aid = ((Tmenu)dbUtil.findById(Tmenu.class, id)).getFk_aid();
			dbUtil.del(Tmenu.class, id);
			dbUtil.del(Tarticle.class, aid);
		}else if (level.equals("parent")) {
			Tmenu menu = (Tmenu)dbUtil.findById(Tmenu.class, id);
			//���������Ӳ˵���ɾ���Ӳ˵��͹���������
			ArrayList<Tmenu> menubox =(ArrayList<Tmenu>) dbUtil.find(Tmenu.class, "pid", menu.getId());
			for (Tmenu m : menubox) {
				//delete from Tmenu where id in (1,3,2);
				dbUtil.del(Tarticle.class, m.getFk_aid());
				dbUtil.del(Tmenu.class, m.getId());
			}
			//���Լ�ɾ��
			dbUtil.del(Tmenu.class, menu.getId());
		}
	
	}
	public void findMenu(int pid,HttpServletResponse resp){
		try {
			//��Ŀ¼������˳���posid��С����
			ArrayList<Tmenu> tmenubox = (ArrayList<Tmenu>)dbUtil.find(Tmenu.class, "pid", pid,"posid","asc");
			StringBuilder sb=new StringBuilder();
			for (Tmenu m : tmenubox) {
				sb.append("id="+m.getId()+",").append("title="+m.getTitle()+",").append("pid="+m.getPid()+"|");
			}
			//��������UTF-8�ı������
			resp.setCharacterEncoding("UTF-8");
			//ȥ�����һ��|
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
			//Ψ���ۣ���֤��(Ѱ����-->˼�ⴧ��-->�����ܽ�-->�߼�����-->����)
			//Ψ���ۣ�����  ���� (����-->�������� ����)
			ArrayList<?> box = dbUtil.find(Tadmin.class, "username", username);
			if(!box.isEmpty()){ //����Ϊnull,�����ж������Ƿ���ֵ
				Tadmin admin=(Tadmin) box.get(0);
				if(admin.getPassword().equals(password)){
					req.getSession().setAttribute("admin", admin);
					Cookie uCookie = new Cookie("username", admin.getUsername());
					uCookie.setPath("/");	//����ֻ�и�Ŀ¼�ܷ���cookie
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
	//�����Ŀ¼��ʱ��,��ȡ��Ŀ¼����������
	public void getData(int id,HttpServletResponse resp){
		try {
			resp.setCharacterEncoding("UTF-8");
			Tmenu menu = (Tmenu)dbUtil.findById(Tmenu.class, id);
			//��ȡ���µ�����
			int aid = menu.getFk_aid();
			Tarticle article = (Tarticle)dbUtil.findById(Tarticle.class, aid);
			if(article==null){
				article=new Tarticle();
				article.setContent("<h1>�����롭��</h1>");
				dbUtil.save(article);
				menu.setFk_aid(article.getId());
				dbUtil.update(menu);
			}
			resp.getWriter().print(article.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//��ctrl+s��ʱ�򱣴���������(һ��Ŀ¼һ������)
	public void saveData(int id,String content){
		Tmenu menu = (Tmenu)dbUtil.findById(Tmenu.class, id);
		int aid = menu.getFk_aid();
		Tarticle article = (Tarticle)dbUtil.findById(Tarticle.class, aid);
		article.setContent(content);
		dbUtil.update(article);
	}
	//��session��cookieʧЧ
	public void logout(HttpServletRequest req,HttpServletResponse resp){
		try {
			HttpSession session = req.getSession();
			Tadmin admin = (Tadmin)session.getAttribute("admin");
			Cookie uCookie = new Cookie("username", admin.getUsername());
			uCookie.setPath("/");	//����ֻ�и�Ŀ¼�ܷ���cookie
			uCookie.setMaxAge(0);//�ÿͻ���cookieʧЧ
			Cookie pCookie = new Cookie("password", admin.getPassword());
			pCookie.setPath("/");
			pCookie.setMaxAge(0);//�ÿͻ���cookieʧЧ
			resp.addCookie(uCookie);
			resp.addCookie(pCookie);
			session.invalidate();//�ݻٵ�ǰsession
			resp.sendRedirect("/");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}