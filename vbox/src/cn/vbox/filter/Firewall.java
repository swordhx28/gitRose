package cn.vbox.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Firewall implements Filter {
	private String[] passurl;
	public void init(FilterConfig config) throws ServletException {
                //读取Filter中的passurl配置参数的值,
		passurl=config.getInitParameter("passurl").split(",");
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI(); //index/logout.jhtml
		//如果访问的url在背放行的url中，则直接放行
		for(String x:passurl){
			if (uri.contains(x)) {
				chain.doFilter(request, response);
				return; //直接跳出当前filter
			}
		}
		// 访问第一个页面是没有refered的！从跳转页开始就有refered
		String refer = req.getHeader("Referer");
		// 设置防火墙，不能从 浏览器直接输入我们其他页面地址
		// 只能完成从主页一个入口进入后内部跳转
		// 判断是否是输入的主页地址
		// http://localhost:8080/add.jhtml
		if (refer == null) {
			// 是首页
			if (uri.equals("/")) {
				chain.doFilter(request, response);// 放行filter
			} else {
				resp.sendRedirect("/");// 打回主页 重定向找谁？！！web.xml
			}
		} else {
			chain.doFilter(request, response);
		}
	}
	public void destroy() {

	}
}