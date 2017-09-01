package 反射.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

import 反射.bean.Foo;
import 反射.bean.MyInvocationHandler;

public class 动态代理 {

	@Test
	public void test() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		InvocationHandler handler = new MyInvocationHandler();
		// 方法1(获取创建的代理类，然后使用反射获得其构造函数，传入Handler为参数，然后实例化一个代理对象)
		Class<?> proxyClass = Proxy.getProxyClass(Foo.class.getClassLoader(), Foo.class);
		Foo f1 = (Foo) proxyClass.getConstructor(InvocationHandler.class).newInstance(handler);
		// 方法2(直接获取代理对象)
		Foo f2 = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(), new Class<?>[] { Foo.class }, handler);

		//通过调用方式可以猜想出来，jdk对动态代理的实现比较统一。
		//至少动态代理类是可以通过代理的接口对象返回的，不做任何接口实现，直接就能返回一个代理类？
		//真正的实现通过构造函数的时候才传入handler。
		//所以可以猜想，Proxy.getProxyClass的原理如下：（一旦自己想的哈）
		//首先根据传入接口生成类，实现每个方法，方法内容很简单，直接调用handler.invoke(Object proxy, Method method, Object[] args)
		
	}
	
	// 动态代理常被应用到以下几种情况中
	//
	// 数据库连接以及事物管理
	// 单元测试中的动态Mock对象
	// 自定义工厂与依赖注入（DI）容器之间的适配器
	// 类似AOP的方法拦截器

}
