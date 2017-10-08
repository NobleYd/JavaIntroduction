package 常用类.集合类.set;

import java.util.EnumMap;
import java.util.EnumSet;

/***
 * java.util.EnumSet
 * 
 * java.util.EnumMap
 * 
 * 当map的键值类型，set的元素类型为枚举类型的时候使用这些实现比直接使用HashMao，HashSet更加高效。
 * 
 * 
 * @author nobleyd
 *
 */
public class EnumMapAndEnumSet {
	static enum DemoEnum {
		enumValue0, enumValue1, enumValue2, enumValue3, enumValue4
	}

	public static void outputFrom1To(int n) {
		StringBuilder sb = new StringBuilder("val0");

		for (int i = 1; i < n; i++) {
			sb.append(", val" + i);
		}

		System.out.println(sb.toString());
	}

	public static void main(String[] args) {
		// outputFrom1To(5000);
		// EnumSet<Enum<E>>
		EnumSet<DemoEnum> enumSet = EnumSet.noneOf(DemoEnum.class);
		System.out.println(enumSet);
		enumSet = EnumSet.allOf(DemoEnum.class);
		System.out.println(enumSet);
		enumSet = EnumSet.of(DemoEnum.enumValue0);
		System.out.println(enumSet);
		enumSet = EnumSet.of(DemoEnum.enumValue0, DemoEnum.enumValue1);
		System.out.println(enumSet);

		// EnumMap
		//可以查看内部实现
		EnumMap<DemoEnum, Integer> enumMap = new EnumMap<>(DemoEnum.class);
		enumMap.put(DemoEnum.enumValue0, 1);
	}

}
