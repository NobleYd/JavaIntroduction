package java虚拟机参数;

import java.util.ArrayList;
import java.util.List;

/***
 * -XX:+PrintGC
 * 
 * [GC (Allocation Failure) -- 2828862K->2828870K(2872832K), 0.0215638 secs]<br/>
 * [Full GC (Ergonomics) 2828870K->2826578K(2872832K), 0.0561893 secs]<br/>
 * [GC (Allocation Failure) -- 2826578K->2826586K(2872832K), 0.0383681 secs]<br/>
 * [Full GC (Allocation Failure) 2826586K->2826566K(2872832K), 0.0197243 secs]<br/>
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 
 * <br/>
 * a->b(c) d secs<br/>
 * a：gc之前堆内存使用量；b：gc之后堆内存使用量；c：当前可用堆内存总和。
 * 
 * 
 * 
 * -XX:+PrintGCDetails<br/>
 * 
 * [GC (Allocation Failure) --[PSYoungGen: 43581K->43581K(76288K)] 2828862K->2828870K(2872832K), 0.0224811 secs] [Times: user=0.17 sys=0.00, real=0.03
 * secs]<br/>
 * [Full GC (Ergonomics) [PSYoungGen: 43581K->41296K(76288K)] [ParOldGen: 2785289K->2785282K(2796544K)] 2828870K->2826578K(2872832K), [Metaspace:
 * 2671K->2671K(1056768K)], 0.0535448 secs] [Times: user=0.07 sys=0.09, real=0.05 secs]<br/>
 * [GC (Allocation Failure) --[PSYoungGen: 41296K->41296K(76288K)] 2826578K->2826578K(2872832K), 0.0443608 secs] [Times: user=0.22 sys=0.00, real=0.05
 * secs] <br/>
 * [Full GC (Allocation Failure) [PSYoungGen: 41296K->41284K(76288K)] [ParOldGen: 2785282K->2785282K(2796544K)] 2826578K->2826566K(2872832K),
 * [Metaspace: 2671K->2671K(1056768K)], 0.0146313 secs] [Times: user=0.03 sys=0.00, real=0.01 secs]<br/>
 * Exception in thread "main" Heap<br/>
 * PSYoungGen total 76288K, used 43250K [0x000000076ab00000, 0x0000000770000000, 0x00000007c0000000)<br/>
 * -->大约74.5M <br/>
 * eden space 65536K, 65% used [0x000000076ab00000,0x000000076d53cae0,0x000000076eb00000)<br/>
 * -->eden大约64M <br/>
 * from space 10752K, 0% used [0x000000076f580000,0x000000076f580000,0x0000000770000000)<br/>
 * -->from大约10.5M <br/>
 * to space 10752K, 0% used [0x000000076eb00000,0x000000076eb00000,0x000000076f580000)<br/>
 * -->(大概判断下SurvivorRatio是6) <br/>
 * ParOldGen total 2796544K, used 2785282K [0x00000006c0000000, 0x000000076ab00000, 0x000000076ab00000)<br/>
 * object space 2796544K, 99% used [0x00000006c0000000,0x000000076a0009b8,0x000000076ab00000)<br/>
 * Metaspace used 2703K, capacity 4486K, committed 4864K, reserved 1056768K<br/>
 * class space used 293K, capacity 386K, committed 512K, reserved 1048576K<br/>
 * java.lang.OutOfMemoryError: Java heap space<br/>
 * at java虚拟机参数.跟踪垃圾回收$DemoObject.<init>(跟踪垃圾回收.java:26)<br/>
 * at java虚拟机参数.跟踪垃圾回收.main(跟踪垃圾回收.java:32)<br/>
 * 
 * 
 */
public class 跟踪垃圾回收 {

	public static class DemoObject {
		int[] data = new int[10 * 1024 * 1024];
	}

	public static void main(String[] args) {
		List<DemoObject> list = new ArrayList<DemoObject>();
		for (int i = 0; i < 1000; i++) {
			list.add(new DemoObject());
		}
	}

}
