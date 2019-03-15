package dontai;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Animal animal = (Animal) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[] { Animal.class }, new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// TODO Auto-generated method stub
						System.out.println(proxy.getClass().getName() + "->" + method.getName() + "()");
						if (method.getName().equals("work")) {
							Dog dog = new Dog();
							return method.invoke(dog, args);
						}
						return null;
					}
				});
		animal.work("Äã¸öÖí¶¼¿ì");
	}
}
