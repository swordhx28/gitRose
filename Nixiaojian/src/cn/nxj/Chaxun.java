package cn.nxj;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import nixiaojian.Main;


public class Chaxun {
		private Connection conn;
		private PreparedStatement stm;
		private ResultSet rs;
		
		public Chaxun() {
			try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/box?useUnicode=true&characterEncoding=utf-8", "root", "123");
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public Object findById(Class<?> cl,int id) {
			Object obj=null;
			Field[] fi= cl.getDeclaredFields();
			String sql = "select * from "+ cl.getSimpleName().toLowerCase()+ " where "+fi[0].getName()+" = ?";
			try {
				stm = conn.prepareStatement(sql);
				stm.setObject(1, id);
				rs = stm.executeQuery();
				if(rs.next()) {
					obj = cl.newInstance();
					for (Field f : fi) {
						f.setAccessible(true);
						f.set(obj,rs.getObject(f.getName()));
						
					}
					rs.close();
					stm.close();
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return obj;
			
		}
		public static void main(String[] args) {
			Chaxun main  = new Chaxun();
			Animal animal = (Animal)main.findById(Animal.class,2);
			System.out.println(animal);
		}
}
