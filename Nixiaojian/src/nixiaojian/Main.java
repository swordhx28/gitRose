package nixiaojian;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/box?useUnicode=true&characterEncoding=utf-8","root","123");
			Statement statement = conn.createStatement();
			Scanner scanner = new Scanner(System.in);
			System.out.print("请输入：");
			String name = scanner.next();
			ResultSet rs = statement.executeQuery("select id,name from Animal where name ='"+name+"'");
			while (rs.next()) {
				System.out.println(rs.getInt("id")+":"+rs.getString("name"));
			}
			rs.close();
			scanner.close();
			statement.cancel();
			conn.close();
			/*PreparedStatement statement = conn.prepareStatement("update Animal set name='人工智能' where id =3;");
			statement.executeUpdate();
			statement.close();
			conn.close();*/
			/* 
			PreparedStatement statement = conn.prepareStatement("select * from Animal");
			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String name=rs.getString("name");
				System.out.println(id+":"+name);
			}
			rs.close();
			statement.close();
			conn.close();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	
