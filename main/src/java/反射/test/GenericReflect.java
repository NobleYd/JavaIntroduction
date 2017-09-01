package 反射.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import 反射.bean.MyClass;

public class GenericReflect {

	// 泛型方法返回类型
	@Test
	public void test1() throws NoSuchMethodException, SecurityException {
		Method method = MyClass.class.getMethod("getStringList", null);
		System.out.println(method);
		System.out.println(method.getReturnType());// interface java.util.List
		Type returnType = method.getGenericReturnType();
		System.out.println(returnType);
		if (returnType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) returnType;
			Type[] argTypes = parameterizedType.getActualTypeArguments();
			for (Type argType : argTypes) {
				Class typeArgClass = (Class) argType;
				System.out.println("typeArgClass = " + typeArgClass);
			}
		} else {
			System.out.println("Not ParameterizedType");
		}
	}

	// 泛型方法参数类型
	@Test
	public void test2() throws NoSuchMethodException, SecurityException {
		Method method = MyClass.class.getMethod("setStringList", List.class);

		Type[] genericParameterTypes = method.getGenericParameterTypes();

		for (Type genericParameterType : genericParameterTypes) {
			System.out.println(genericParameterType);
			if (genericParameterType instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) genericParameterType;
				Type[] parameterArgTypes = parameterizedType.getActualTypeArguments();
				for (Type parameterArgType : parameterArgTypes) {
					Class parameterArgClass = (Class) parameterArgType;
					System.out.println("parameterArgClass = " + parameterArgClass);
				}
			}
		}
	}

	// 泛型变量类型
	@Test
	public void test3() throws NoSuchFieldException, SecurityException {
		Field field = MyClass.class.getDeclaredField("stringList");
		System.out.println(field.getType());
		Type genericFieldType = field.getGenericType();
		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = parameterizedType.getActualTypeArguments();
			for (Type fieldArgType : fieldArgTypes) {
				Class fieldArgClass = (Class) fieldArgType;
				System.out.println("fieldArgClass = " + fieldArgClass);
			}
		}
	}

	@Test
	public void test4() {
		// 这个例子说明一下java中泛型只是编译时检查哦
		// 运行期是擦除的

		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);

		// list.add("hello world");//编译报错，过不去。
		// 想法子，如下：
		List list2 = list;// 赋值给另外一个List即可。
		list2.add("hello world");

		((List) (list)).add("哈哈");
		((List) (list)).add(new MyClass());

		System.out.println(list);
	}

}
