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
	// ����Id��ȡ����
	public Object findById(Class<?> cl, int id) {
		Object obj = null;
		Field[] fi = cl.getDeclaredFields();// ��ȡ���е����е�����
		String sql = "select * from " + cl.getSimpleName().toLowerCase()
				+ " where " + fi[0].getName() + " = ?";
		try {
			Connection conn = pool.peek().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setObject(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				obj = cl.newInstance();// ����ָ�����ʵ��������
				for (Field f : fi) {
					f.setAccessible(true);// ��ʾ���Է������е�˽������
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
		Field[] fi = clz.getDeclaredFields();// ��ȡ���е����е�����
		StringBuilder sb = new StringBuilder();
		sb.append("update ");
		sb.append(clz.getSimpleName().toLowerCase());
		sb.append(" set ");
		for (int i = 1; i < fi.length; i++) {
			sb.append(fi[i].getName());// ��ȡ����
			sb.append(" =? ");
			if (i != fi.length - 1) {// ���һ�в��üӶ���
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
			// ��������
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
			String table = clz.getSimpleName().toLowerCase();// ���ݱ���
			Field[] fi = clz.getDeclaredFields();// �ֶ���
			StringBuilder sb = new StringBuilder();// ƴװsql�����
			// ƴװsql���
			sb.append("insert into ").append(table).append(" (");
			for (int i = 0; i < fi.length - 1; i++) {
				sb.append(fi[i].getName() + ",");
			}
			sb.append(fi[fi.length - 1].getName() + ") values (");// ���һ�����Ӷ���
			for (int i = 0; i < fi.length - 1; i++) {
				sb.append("?,");
			}
			sb.append("?)");// ���һ�����Ӷ���
			// ����ֵ
			Connection conn = pool.peek().getConnection();
			PreparedStatement stm = conn.prepareStatement(sb.toString());
			for (int i = 0; i < fi.length; i++) {
				fi[i].setAccessible(true);// ���������ֶ�
				stm.setObject(i + 1, fi[i].get(ob));
			}
			stm.executeUpdate();
			//����ĳ������һ��������id��ֵ
			String sql="select auto_increment from  information_schema.tables where table_schema='mydata' and table_name='"+table+"'";
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				//��id��ֵ
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
		// Ҫ��:���ݱ��е��ֶα��������е�����һһ��Ӧ
		Field[] fi = clazz.getDeclaredFields();// ��ȡ���е����е�����
		String sql = "select * from "+ clazz.getSimpleName().toLowerCase();// ��ȡ����(�������������ݱ�һ��)
		try {
			Connection conn = pool.peek().getConnection();
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Object obj = clazz.newInstance();// ����ָ�����ʵ��������
				for (Field f : fi) {
					f.setAccessible(true);// ��������
					f.set(obj, rs.getObject(f.getName()));// ��������ָ�����Ե�set������ֵ
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
		Field[] fi = cl.getDeclaredFields();// ��ȡ���е����е�����
		String sql = "select * from "+ cl.getSimpleName().toLowerCase()+ " where " + name + " = '" + value + "'";
		try {
			Connection conn = pool.peek().getConnection();
			PreparedStatement stm =  conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Object obj = cl.newInstance();
				for (Field f : fi) {
					f.setAccessible(true);// ��������
					f.set(obj, rs.getObject(f.getName()));// ��������ָ�����Ե�set������ֵ
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
        //��ѯ��ʱ�����posid�Ӵ�С��˳������
	public ArrayList<?> find(Class<?> cl, String name, Object value,String column,String desc) {
		ArrayList<Object> list = new ArrayList<Object>();
		Field[] fi = cl.getDeclaredFields();// ��ȡ���е����е�����
		String sql = "select * from "+ cl.getSimpleName().toLowerCase()+ " where " + name + " = '" + value + "' order by "+column+" "+desc;
		try {
			Connection conn = pool.peek().getConnection();
			PreparedStatement stm =  conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Object obj = cl.newInstance();
				for (Field f : fi) {
					f.setAccessible(true);// ��������
					f.set(obj, rs.getObject(f.getName()));// ��������ָ�����Ե�set������ֵ
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