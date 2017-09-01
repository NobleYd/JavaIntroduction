package 枚举;

public class EnumDemo {

	public static void main(String[] args) {

		// public abstract class Enum<E extends Enum<E>>
		// 首先借这个声明说明一下java这个常见的定义方式。
		// 首先，java经常需要设计一个父类，基础类，并且是基于泛型的。
		// 比如这个类Enum就是一个抽象类，必然是用于被继承才可以使用的。
		// 然后其泛型参数E的意义是什么呢？
		// 这种形式的 定义： class Demo < E extends Demo< E > > 是一种常见的方式。
		// 可以发现这个定义实际存在递归定义。
		// 泛型E是Demo<E>的子类。本身我们当前正在定义Demo<E>。然后这个参数E也是我们正定义的这个类的子类，而且使用的是相同的泛型参数。
		// 听起来好像很别扭。实际也好理解，下面说以下这么做的目的就好理解了。
		
		//目的其实也很简单，因为java中子类赋值给父类引用是不需要强制转换，但反过来却是需要的。
		//所以这个Demo类中某个方法的返回值，返回一个带有泛型参数的类型。比如List<Demo>.
		//这样做会导致将来子类调用这个方法返回的也就是List<Demo>，而这个是无法用子类的List接受的。
		//即 List<ChildDemo> = List<Demo> 是编译报错的。
		//-->事实上，java中即使是：List<Object> list = new ArrayList<Integer>(); 也是编译报错的。
		//更何况我们当前这个例子压根不可能不报错。
		//那么怎么能不报错呢。正确应该是 List<ChildDemo> list = List<ChildDemo>
		//注意使用 List<Demo> = List<Demo> 不大可能。因为本身我们就是要继承Demo，使用具体的子类，自然不可能只需要Demo就行。
		//必须细化到ChildDemo。
		//所以本质上我们的需求是：让Demo类的该方法返回的值类型是 List<ChildDemo> 类型即可。
		//这样子类ChildDemo调用该方法就可以使用List<Demo>直接接受了。
		//而定义Demo类的时候并不存在子类类型，如何能够返回子类类型呢？？？
		//这就是这个泛型参数E的作用了。我们定义子类ChildDemo的时候，将ChildDemo这个子类类型当作参数E传递给父类Demo类即可。
		//所以Demo<E>定以了E参数。为了具体的限制E，加入了 class Demo < E extends Demo< E > > 这些限制。
		
	}

}
