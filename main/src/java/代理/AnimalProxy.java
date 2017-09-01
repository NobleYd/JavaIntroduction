package 代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AnimalProxy {

	public static void main(String[] args) {
		InvocationHandler handler = new InvocationHandler() {

			private Animal animal = new Animal();

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("method: " + method.getName());
				System.out.println("args: " + args);
				return method.invoke(animal, args);
			}
		};

		IAnimal animal = (IAnimal) Proxy.newProxyInstance(AnimalProxy.class.getClassLoader(),
				new Class[] { IAnimal.class }, handler);
		String ans = animal.eat();

		System.out.println(ans);

	}

}
