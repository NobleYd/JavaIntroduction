package guava的使用;

import java.util.HashSet;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;


//Sets
public class 集合 {

	public static void main(String[] args) {
		HashSet<String> hashSet1 = Sets.newHashSet();
		hashSet1.add("a");
		hashSet1.add("b");
		hashSet1.add("c");
		HashSet<String> hashSet2 = Sets.newHashSet();
		hashSet2.add("a");
		hashSet2.add("e");
		
		SetView<String> intersection = Sets.intersection(hashSet1, hashSet2);
		System.out.println(intersection);
		SetView<String> union = Sets.union(hashSet1, hashSet2);
		System.out.println(union);
		
	}
	
}
