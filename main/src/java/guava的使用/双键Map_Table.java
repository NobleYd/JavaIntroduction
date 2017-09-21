package guava的使用;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class 双键Map_Table {

	public static void main(String[] args) {

		Table<String, String, Double> stuCourseScores = HashBasedTable.<String, String, Double>create();
		stuCourseScores.put("张三", "JAVA", 99.9D);
		stuCourseScores.put("张三", "C++", 96D);
		stuCourseScores.put("李四", "JAVA", 95D);
		stuCourseScores.put("李四", "C++", 66D);

		System.out.println(stuCourseScores);
		System.out.println(stuCourseScores.cellSet());
		System.out.println(stuCourseScores.rowKeySet());
		System.out.println(stuCourseScores.columnKeySet());
		System.out.println(stuCourseScores.row("张三"));// 获取某行的map
		System.out.println(stuCourseScores.column("JAVA"));// 获取某列的map
		System.out.println(stuCourseScores.rowMap());
		System.out.println(stuCourseScores.columnMap());
		
	}

}
