package cn.vbox.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.vbox.server.IData;
import cn.vbox.server.TrackerServer;
public class ImageView extends HttpServlet{
	private static TrackerServer trackerServer=TrackerServer.startup("localhost", 9999);
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    doPost(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//http://localhost:80/m00/group/8b/02/8b02571f-f157-4b2d-ad17-bab2dc34e761-000-000.jpg
		String  url= req.getRequestURL().toString();
		url=url.substring(url.lastIndexOf("/")-15);
		IData data = trackerServer.receive(url);
		resp.getOutputStream().write(data.getBuf(),0,data.getLen());
	}
}