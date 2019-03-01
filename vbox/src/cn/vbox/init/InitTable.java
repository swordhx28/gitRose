package cn.vbox.init;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import cn.vbox.annotation.Column;
import cn.vbox.annotation.Table;
import cn.vbox.dao.DBUtil;
public class InitTable implements ServletContextListener{
	public void contextDestroyed(ServletContextEvent event) {
	}
	public void contextInitialized(ServletContextEvent event) {
			try {
				ResourceBundle bundle = ResourceBundle.getBundle("project");
				String[] clazzBox=bundle.getString("className").split("#");
				for (String c : clazzBox) {
					Class<?> clazz = Class.forName(c);
					boolean present = clazz.isAnnotationPresent(Table.class);
					if (present == false) {
						return;
					}
					String tableName = clazz.getAnnotation(Table.class).name();
					StringBuilder sb = new StringBuilder();
					sb.append("create table if not exists " + tableName + " (");
					Field[] fields = clazz.getDeclaredFields();
					for (Field field : fields) {
						if(field.getName().equals("serialVersionUID")){continue;}
						Column column = field.getAnnotation(Column.class);
						sb.append(column.name() + " ").append(column.type() + " ").append(column.isNull() + " ");
						if (column.primaryId() == true) {
							sb.append("primary key auto_increment,");
						} else {
							sb.append(",");
						}
					}
					sb.delete(sb.length() - 1, sb.length()).append(")");
					System.out.println(sb.toString());
					Connection conn = DBUtil.pool.peek().getConnection();
					PreparedStatement psm = conn.prepareStatement(sb.toString());
					psm.execute();
					psm.close();
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
