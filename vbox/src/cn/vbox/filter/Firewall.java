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
                //��ȡFilter�е�passurl���ò�����ֵ,
		passurl=config.getInitParameter("passurl").split(",");
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI(); //index/logout.jhtml
		//������ʵ�url�ڱ����е�url�У���ֱ�ӷ���
		for(String x:passurl){
			if (uri.contains(x)) {
				chain.doFilter(request, response);
				return; //ֱ��������ǰfilter
			}
		}
		// ���ʵ�һ��ҳ����û��refered�ģ�����תҳ��ʼ����refered
		String refer = req.getHeader("Referer");
		// ���÷���ǽ�����ܴ� �����ֱ��������������ҳ���ַ
		// ֻ����ɴ���ҳһ����ڽ�����ڲ���ת
		// �ж��Ƿ����������ҳ��ַ
		// http://localhost:8080/add.jhtml
		if (refer == null) {
			// ����ҳ
			if (uri.equals("/")) {
				chain.doFilter(request, response);// ����filter
			} else {
				resp.sendRedirect("/");// �����ҳ �ض�����˭������web.xml
			}
		} else {
			chain.doFilter(request, response);
		}
	}
	public void destroy() {

	}
}