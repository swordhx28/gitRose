package cn.vbox.dao;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Stack;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
public class DBUtil {
	public static Stack<DataSource> pool = new Stack<DataSource>();
	static {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mydata");
			pool.push(ds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 根据Id获取对象
	public Object findById(Class<?> cl, int id) {
		Object obj = null;
		Field[] fi = cl.getDeclaredFields();// 获取类中的所有的属性
		String sql = "select * from " + cl.getSimpleName().toLowerCase()
				+ " where " + fi[0].getName() + " = ?";
		try {
			Connection conn = pool.peek().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setObject(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				obj = cl.newInstance();// 创建指定类的实例化对象
				for (Field f : fi) {
					f.setAccessible(true);// 表示可以访问类中的私有属性
					f.set(obj, rs.getObject(f.getName()));
				}
			}
			rs.close();
			stm.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	public void update(Object obj) {
		Class<?> clz = obj.getClass();
		Field[] fi = clz.getDeclaredFields();// 获取类中的所有的属性
		StringBuilder sb = new StringBuilder();
		sb.append("update ");
		sb.append(clz.getSimpleName().toLowerCase());
		sb.append(" set ");
		for (int i = 1; i < fi.length; i++) {
			sb.append(fi[i].getName());// 获取列名
			sb.append(" =? ");
			if (i != fi.length - 1) {// 最后一列不用加逗号
				sb.append(",");
			}
		}
		sb.append(" where ");
		sb.append(fi[0].getName());
		sb.append(" =? ");
		try {
			Connection conn = pool.peek().getConnection();
			PreparedStatement stm = conn.prepareStatement(sb.toString());
			for (int i = 1; i < fi.length; i++) {
				fi[i].setAccessible(true);
				stm.setObject(i, fi[i].get(obj));
			}
			// 设置主键
			fi[0].setAccessible(true);
			stm.setObject(fi.length, fi[0].get(obj));
			stm.executeUpdate();
			stm.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void save(Object ob) {
		try {
			Class<?> clz = ob.getClass();
			String table = clz.getSimpleName().toLowerCase();// 数据表名
			Field[] fi = clz.getDeclaredFields();// 字段名
			StringBuilder sb = new StringBuilder();// 拼装sql语句用
			// 拼装sql语句
			sb.append("insert into ").append(table).append(" (");
			for (int i = 0; i < fi.length - 1; i++) {
				sb.append(fi[i].getName() + ",");
			}
			sb.append(fi[fi.length - 1].getName() + ") values (");// 最后一个不加逗号
			for (int i = 0; i < fi.length - 1; i++) {
				sb.append("?,");
			}
			sb.append("?)");// 最后一个不加逗号
			// 设置值
			Connection conn = pool.peek().getConnection();
			PreparedStatement stm = conn.prepareStatement(sb.toString());
			for (int i = 0; i < fi.length; i++) {
				fi[i].setAccessible(true);// 暴力访问字段
				stm.setObject(i + 1, fi[i].get(ob));
			}
			stm.executeUpdate();
			//查找某表的最后一次自增长id的值
			String sql="select auto_increment from  information_schema.tables where table_schema='mydata' and table_name='"+table+"'";
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				//给id赋值
				fi[0].set(ob, (rs.getInt(1)-1));
			}
			stm.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void del(Class<?> clazz,int id) {
		try {
		 String sql="delete from "+clazz.getSimpleName().toUpperCase()+" where id=?";
		 Connection conn = pool.peek().getConnection();
		 PreparedStatement stm= conn.prepareStatement(sql);
		 stm.setObject(1, id);
		 stm.executeUpdate();
		 stm.close();
		 conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Object> findAll(Class<?> clazz) {
		ArrayList<Object> list = new ArrayList<Object>();
		// 要求:数据表中的字段必须与类中的属性一一对应
		Field[] fi = clazz.getDeclaredFields();// 获取类中的所有的属性
		String sql = "select * from "+ clazz.getSimpleName().toLowerCase();// 获取表名(类名必须与数据表一致)
		try {
			Connection conn = pool.peek().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Object obj = clazz.newInstance();// 创建指定类的实例化对象
				for (Field f : fi) {
					f.setAccessible(true);// 暴力访问
					f.set(obj, rs.getObject(f.getName()));// 调用类中指定属性的set方法赋值
				}
				list.add(obj);
			}
			rs.close();
			stm.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<?> find(Class<?> cl, String name, Object value) {
		ArrayList<Object> list = new ArrayList<Object>();
		Field[] fi = cl.getDeclaredFields();// 获取类中的所有的属性
		String sql = "select * from "+ cl.getSimpleName().toLowerCase()+ " where " + name + " = '" + value + "'";
		try {
			Connection conn = pool.peek().getConnection();
			PreparedStatement stm =  conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Object obj = cl.newInstance();
				for (Field f : fi) {
					f.setAccessible(true);// 暴力访问
					f.set(obj, rs.getObject(f.getName()));// 调用类中指定属性的set方法赋值
				}
				list.add(obj);
			}
			rs.close();
			stm.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
        //查询的时候根据posid从大到小的顺序排列
	public ArrayList<?> find(Class<?> cl, String name, Object value,String column,String desc) {
		ArrayList<Object> list = new ArrayList<Object>();
		Field[] fi = cl.getDeclaredFields();// 获取类中的所有的属性
		String sql = "select * from "+ cl.getSimpleName().toLowerCase()+ " where " + name + " = '" + value + "' order by "+column+" "+desc;
		try {
			Connection conn = pool.peek().getConnection();
			PreparedStatement stm =  conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Object obj = cl.newInstance();
				for (Field f : fi) {
					f.setAccessible(true);// 暴力访问
					f.set(obj, rs.getObject(f.getName()));// 调用类中指定属性的set方法赋值
				}
				list.add(obj);
			}
			rs.close();
			stm.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}