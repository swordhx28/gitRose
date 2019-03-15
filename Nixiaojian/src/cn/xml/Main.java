package cn.xml;

import org.apache.commons.configuration.XMLConfiguration;

public class Main {
	public static void main(String[] args) {
		try {
			XMLConfiguration config = new XMLConfiguration("student.xml");
			String contry = config.getString("stu(0)[@contry]");
			String name = config.getString("stu(0).name");
			System.out.println(contry);
			System.out.println(name);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}