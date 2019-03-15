package TCPwan;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
/**
 * @author wuqiwei
 * @since 2018
 * @version xx
 * */ 
//异常也是一种日志信息 运行时异常和非运行时异常都是分等级的 日志 fatal error info warn debug
public class Log {
	// common-io.jar
	// java在控制你，还是你在控制java
	//1.等级 2时间 3信息 4代码报错具体位置
	private static FileWriter logout;
	private static final String basepath = ResourceBundle.getBundle("project").getString("basepath");
	private static final String logname = ResourceBundle.getBundle("project").getString("logname");
	private static String time = new SimpleDateFormat("yyyy-MM-dd-hh_mm_ss").format(new Date());
	public static final String logpath=basepath + "/" + time + "." +logname;
	private Log() {
		
	}
	
	static{
		try {
			System.out.println(logpath);
			System.out.println("Hello world");
			logout=new FileWriter(logpath);
			File file=new File(logpath);
			file.createNewFile();
			//24:java 版本控制
			System.out.println("xxx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//java android php+linux
	public static void error(String msg) {
		try {
			String time = new SimpleDateFormat("yyyy-DD-mm-hh:mm:ss").format(new Date());
			logout.write("error:"+time+":"+msg);
			logout.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void fatal(String msg){
		try {
			String time = new SimpleDateFormat("yyyy-DD-mm-hh:mm:ss").format(new Date());
			logout.write("fatal:"+time+":"+msg);
			logout.flush();
			System.out.println("xx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void debug(String msg){
		try {
			String time = new SimpleDateFormat("yyyy-DD-mm-hh:mm:ss").format(new Date());
			logout.write("debug:"+time+":"+msg);
			logout.flush();
			/*Begin:wuqiwei 2018-8-6 001 addReason:调试*/
			System.out.println("xx");
			/*Begin:wuqiwei 2018-8-6 001 addReason:调试*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void info(String msg){
		try {
			String time = new SimpleDateFormat("yyyy-DD-mm-hh:mm:ss").format(new Date());
			logout.write("info:"+time+":"+msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void warn(String msg){
		try {
			String time = new SimpleDateFormat("yyyy-DD-mm-hh:mm:ss").format(new Date());
			logout.write("info:"+time+":"+msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
