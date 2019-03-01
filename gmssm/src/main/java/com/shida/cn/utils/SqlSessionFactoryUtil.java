package com.shida.cn.utils;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtil {
	private static SqlSessionFactory sf;
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	static {
		try {
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			sf = new SqlSessionFactoryBuilder().build(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sf;
	}
	
	public static SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		sqlSession = threadLocal.get();
		if(sqlSession==null) {
			sqlSession = sf.openSession();
			threadLocal.set(sqlSession);
		}
		return sqlSession;
	}
	
	public static void closeSqlSession() {
		SqlSession sqlSession = threadLocal.get();
		if(sqlSession != null) {
			threadLocal.set(null);
			sqlSession.close();
		}
	}
}
