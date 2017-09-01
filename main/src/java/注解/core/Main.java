package 注解.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import 注解.annotation.AnimalAnnotation;
import 注解.zoo.Dog1;
import 注解.zoo.Dog2;

public class Main {

	public static void main(String[] args) {
		Dog1 dog1 = new Dog1();
		Dog2 dog2 = new Dog2();

		Class<? extends Dog1> dog1Class = dog1.getClass();
		Class<? extends Dog2> dog2Class = dog2.getClass();

		/***
		 * 看方法doc可以知道，这是获取该类cls声明的实现的接口（对于cls对应是类情况）<br/>
		 * 或者是继承的接口（对于cls对应是接口情况）
		 */
		AnnotatedType[] dog1AnnotatedInterfaces = dog1Class.getAnnotatedInterfaces();
		AnnotatedType[] dog2AnnotatedInterfaces = dog2Class.getAnnotatedInterfaces();
		System.out.println(
				"dog1Class.getAnnotatedInterfaces() = " + turnAnnotatedTypeToRealTypes(dog1AnnotatedInterfaces));
		System.out.println(
				"dog2Class.getAnnotatedInterfaces() = " + turnAnnotatedTypeToRealTypes(dog2AnnotatedInterfaces));
		System.out.println("----------------------------------------");

		/***
		 * 获取标注的父类，注意这个方法和上一个方法返回的不一样，上一个返回数组，这个方法返回单类型。<br/>
		 * 这是因为java中一个类可以实现多个接口，但却只能继承一个类。
		 */
		AnnotatedType dog1AnnotatedType = dog1Class.getAnnotatedSuperclass();
		AnnotatedType dog2AnnotatedType = dog2Class.getAnnotatedSuperclass();
		System.out.println("dog1Class.getAnnotatedSuperclass() = " + dog1AnnotatedType.getType());
		System.out.println("dog2Class.getAnnotatedSuperclass() = " + dog2AnnotatedType.getType());
		System.out.println("----------------------------------------");

		/***
		 * 从这个方法开始才真正开始和Annotation打交道。之前的Annotation都是动词意义，指标注。之后的特指注解。<br/>
		 * 首先注意，annotation要想被反射找到，必须声明为@Retention(RetentionPolicy.RUNTIME)。<br/>
		 * 下面这个方法getAnnotations可以获取到指定cls上被标注的Annotation的集合。<br/>
		 * 测试发现不包括被标注的Annotation的父Annotation。
		 */
		Annotation[] dog1Annotations = dog1Class.getAnnotations();
		Annotation[] dog2Annotations = dog2Class.getAnnotations();
		System.out.println("dog1Annotations = " + Arrays.asList(dog1Annotations));
		System.out.println("dog2Annotations = " + Arrays.asList(dog2Annotations));
		System.out.println("----------------------------------------");

		/***
		 * 相比上一个方法返回所有的注解，这个方法返回指定注解类型的注解。有返回，无返回null。 <br/>
		 * getAnnotation(AnimalAnnotation.class)方法用于返回cls上被标注的指定Annotation类型的Annotation。
		 */
		AnimalAnnotation dog1AnimalAnnotation = dog1Class.getAnnotation(AnimalAnnotation.class);
		AnimalAnnotation dog2AnimalAnnotation = dog2Class.getAnnotation(AnimalAnnotation.class);
		System.out.println("dog1Class.getAnnotation(AnimalAnnotation.class) = " + dog1AnimalAnnotation);
		System.out.println("dog2Class.getAnnotation(AnimalAnnotation.class) = " + dog2AnimalAnnotation);
		System.out.println("----------------------------------------");

		System.out.println(Arrays.asList(dog1Class.getAnnotationsByType(AnimalAnnotation.class)));
		System.out.println(dog1Class.getDeclaredAnnotation(AnimalAnnotation.class));
		System.out.println(Arrays.asList(dog1Class.getDeclaredAnnotations()));
		System.out.println(Arrays.asList(dog1Class.getDeclaredAnnotationsByType(AnimalAnnotation.class)));

		System.out.println("----------------------------------------");
		
		System.out.println(Arrays.asList(dog2Class.getAnnotationsByType(AnimalAnnotation.class)));
		System.out.println(dog2Class.getDeclaredAnnotation(AnimalAnnotation.class));
		System.out.println(Arrays.asList(dog2Class.getDeclaredAnnotations()));
		System.out.println(Arrays.asList(dog2Class.getDeclaredAnnotationsByType(AnimalAnnotation.class)));

	}

	/***
	 * Type: 是类型，Java中一切类型都是Type，Class类就是Type的子类。
	 * 
	 * @param annotatedTypes
	 *            这个参数是AnnotatedType类型的数组。
	 *            每个AnnotatedType代表封装了一个被标注在某个类或方法或对象等等上的标注。
	 */
	public static List<Type> turnAnnotatedTypeToRealTypes(AnnotatedType[] annotatedTypes) {
		List<Type> types = new ArrayList<>();
		for (AnnotatedType annotatedType : annotatedTypes) {
			types.add(annotatedType.getType());
		}
		return types;
	}

}
