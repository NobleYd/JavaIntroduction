package java虚拟机参数;

//-Xms / -Xmx — 堆的初始大小 / 堆的最大大小
//-Xmn — 堆中年轻代的大小
//-XX:-DisableExplicitGC — 让System.gc()不产生任何作用
//-XX:+PrintGCDetails — 打印GC的细节
//-XX:+PrintGCDateStamps — 打印GC操作的时间戳
//-XX:NewSize / XX:MaxNewSize — 设置新生代大小/新生代最大大小
//-XX:NewRatio — 可以设置老生代和新生代的比例
//-XX:PrintTenuringDistribution — 设置每次新生代GC后输出幸存者乐园中对象年龄的分布
//-XX:InitialTenuringThreshold / -XX:MaxTenuringThreshold：设置老年代阀值的初始值和最大

public class jvm参数 {

}
