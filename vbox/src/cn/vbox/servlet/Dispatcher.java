package cn.vbox.servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispatcher extends HttpServlet {
	private Map<String, String> boxmap = new HashMap<String, String>();
	{
		boxmap.put("double", "java.lang.Double");
		boxmap.put("int", "java.lang.Integer");
		boxmap.put("short", "java.lang.Short");
		boxmap.put("boolean", "java.lang.Boolean");
		boxmap.put("long", "java.lang.Long");
		boxmap.put("char", "java.lang.Character");
		boxmap.put("float", "java.lang.Float");
		boxmap.put("byte", "java.lang.Byte");
		boxmap.put("String", "java.lang.String");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String[] path = req.getServletPath().replaceFirst("/", "").split("/");
			String className = path[0].substring(0, 1).toUpperCase() + path[0].substring(1) + "Servlet";
			String methodName = path[1].split("\\.")[0];
			Class<?> clazz = Class.forName(Dispatcher.class.getPackage().getName() + "." + className);
			// 瑙ｅ喅鑰﹀悎HttpServletRequest鍜孒ttpServletResponse鍙傛暟闂
			for (Method method : clazz.getDeclaredMethods()) {
				if (method.getName().equals(methodName)) {
					// 鑾峰彇浜嗘柟娉曠被鍨�
					Object obj = clazz.newInstance();
					// 鑾峰彇璇ユ柟娉曚腑鎵�湁鍙傛暟鐨勭被鍨�
					Class<?>[] parameterTypes = method.getParameterTypes();
					/* Begin:鑾峰彇琛ㄥ崟鎵�湁鐨勫弬鏁板�鏀惧埌涓�釜闆嗗悎涓�*/
					ArrayList<Object> parameters = new ArrayList<Object>();
					Enumeration<String> parameterNames = req.getParameterNames();
					// 鍥犱负鍙傛暟绫诲瀷鍜屽弬鏁颁釜鏁颁竴涓�搴�
					for (int i = 0; i < parameterTypes.length; i++) {
						String parameterType = parameterTypes[i].getSimpleName();
						// 鍒ゆ柇鏄惁鏄熀鏈暟鎹被鍨�
						if (boxmap.get(parameterType) != null) {
							if (parameterNames.hasMoreElements()) {
								// 閫氳繃鍙傛暟鑾峰彇鍙傛暟鍊�
								String key = (String) parameterNames.nextElement();
								String value = req.getParameter(key);
								// 瀛楃涓茶浆鏁板�绫诲瀷鏄皟鐢↖nteger.parseInt(String)\Double.parseDouble(String)
								String methodN = "";
								Object v = null;
								String t = parameterTypes[i].getSimpleName().substring(0, 1).toUpperCase() + parameterTypes[i].getSimpleName().substring(1);
								if (parameterType.equals("int") || parameterType.equals("byte") || parameterType.equals("long") || parameterType.equals("short")|| parameterType.equals("boolean")) {
									Method m = Class.forName(boxmap.get(parameterType)).getMethod("parse" + t, String.class);
									v = m.invoke(null, value);// 璋冪敤Integer.parseInt("10")灏嗗瓧绗︿覆10杞崲鎴怚nteger绫诲瀷
								} else if ((parameterType.equals("char") )) {
									v=value.charAt(0);
								} else if (parameterType.equals("String")) {
									v = value;
								}
								parameters.add(v);
							}
						} else if (parameterTypes[i].getName().equals("javax.servlet.http.HttpServletRequest")) {
							parameters.add(req);
						} else if (parameterTypes[i].getName().equals("javax.servlet.http.HttpServletResponse")) {
							parameters.add(resp);
						} else {
							System.out.println(parameterTypes[i].getName());
							Object o = parameterTypes[i].newInstance();
							Field[] fields = o.getClass().getDeclaredFields();
							for (int j = 0; j < fields.length; j++) {
								if (parameterNames.hasMoreElements()) {
									// 閫氳繃鍙傛暟鑾峰彇鍙傛暟鍊�
									String key = (String) parameterNames.nextElement();
									String value = req.getParameter(key);
									fields[j].setAccessible(true);
									System.out.println(fields[j]);
									parameterType = fields[j].getType().getSimpleName();
									System.out.println(parameterType);
									// 灏嗙被鍚嶉瀛楁瘝澶у啓
									Object v = null;
									System.out.println( fields[j].getType().getSimpleName());
									String t = fields[j].getType().getSimpleName().substring(0, 1).toUpperCase() + fields[j].getType().getSimpleName().substring(1);
									if (parameterType.equals("int") || parameterType.equals("byte") || parameterType.equals("long") || parameterType.equals("short") || parameterType.equals("boolean")) {
										Method m = Class.forName(boxmap.get(parameterType)).getMethod( "parse" + t, String.class);
										v = m.invoke(null, value);// 璋冪敤Integer.parseInt("10")灏嗗瓧绗︿覆10杞崲鎴怚nteger绫诲瀷
									} else if ((parameterType.equals("char"))) {
										v=value.charAt(0);
									} else if (parameterType.equals("String")) {
										v = value;
									}
									fields[j].set(o, v);
								}
							}
							parameters.add(o);
						}
					}
					method.setAccessible(true);
					method.invoke(obj, parameters.toArray());// 鏁扮粍涓兘鏄疭tring
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}