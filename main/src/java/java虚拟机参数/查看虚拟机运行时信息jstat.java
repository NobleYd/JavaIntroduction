package java虚拟机参数;

import java.util.Scanner;

/***
 * 
 * Usage: jstat -help|-options <br/>
 * jstat -<option> [-t] [-h<lines>] <vmid> [<interval> [<count>]]
 * 
 * 注意 option 部分是必选。
 * 
 * Virtual Machine Identifier. A vmid takes the following form: <lvmid>[@<hostname>[:<port>]] Where <lvmid> is the local vm identifier for the target
 * Java virtual machine, typically a process id; <hostname> is the name of the host running the target Java virtual machine; and <port> is the port
 * number for the rmiregistry on the target host. See the jvmstat documentation for a more complete description of the Virtual Machine Identifier.
 * 
 * 1 jstat –class <pid> : 显示加载class的数量，及所占空间等信息。<br/>
 * Loaded;Bytes;;Unloaded;Bytes;Time <br/>
 * 607;;;;1198.8;0;;;;;;;;0.0;;;0.05 Loaded:装载的类的数量
 * 
 * Bytes:装载类所占用的字节数
 * 
 * Unloaded:卸载类的数量
 * 
 * Bytes:卸载类的字节数
 * 
 * Time:装载和卸载类所花费的时间
 * 
 * 2 jstat -compiler <pid>显示VM实时编译的数量等信息。 Compiled Failed Invalid Time FailedType FailedMethod<br/>
 * 74 0 0 0.02 0
 * 
 * 
 * 3 jstat -gc <pid>: 可以显示gc的信息，查看gc的次数，及时间。
 * 
 * 4 jstat -gc <pid><br/>
 * S0C S1C S0U S1U EC EU OC OU MC MU CCSC CCSU YGC YGCT FGC FGCT GCT <br/>
 * 10752.0 10752.0 0.0 0.0 65536.0 2621.5 175104.0 0.0 4480.0 774.5 384.0 75.9 0 0.000 0 0.000 0.000
 * 
 * S0C -->survivor0<br/>
 * S1C<br/>
 * S0U -->survivor0中已经使用的大小<br/>
 * 
 * EC -->eden区<br/>
 * EU -->eden区已使用大小<br/>
 * 
 * OC --> old 年老代<br/>
 * OU<br/>
 * 
 * PC --> permant space<br/>
 * PU --> 永久区已经使用大小<br/>
 * <br/>
 * 
 * YGC -->young gc的次数<br/>
 * YGCT-->young gc的时间<br/>
 * FGC -->full gc的次数<br/>
 * FGCT-->full gc的时间<br/>
 * GCT -->gc的时间
 */

/***
 * 还有一个jstatd。
 * 
 * 这个是和jstat有点关系的，上边我们介绍jstat的<vmid>的时候可以发现，vmid可能是一个远程主机上的vm。
 * 
 * 要实现远程主机上的vm的监控，就需要在远程主机上运行jstatd。这是一个RMI服务端程序，相当于代理服务器，建立本地计算机和远程监控工具之间的通信。
 * 
 * jstatd将本地java应用程序的信息传递到远程计算机。
 * 
 * @author nobleyd
 *
 */
public class 查看虚拟机运行时信息jstat {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		scanner.next();
		scanner.close();
	}
}
