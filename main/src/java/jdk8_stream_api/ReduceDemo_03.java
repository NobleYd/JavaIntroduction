package jdk8_stream_api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.Test;

/***
 * 汇聚
 * 
 * 可变汇聚：把输入的元素们累积到一个可变的容器中，比如Collection或者StringBuilder;
 * 
 * 其他汇聚：除去可变汇聚剩下的，一般都不是通过反复修改某个可变对象，而是通过把前一次的汇聚结果当成下一次的入参，反复如此。比如reduce，count，allMatch;
 *
 */
public class ReduceDemo_03 {

	// 可变汇聚 mutable reduction
	/***
	 * 可变汇聚对应的只有一个方法：collect.
	 * 
	 * 正如其名字显示的，它可以把Stream中的要有元素收集到一个结果容器中（比如Collection）。
	 * 
	 * 先看一下最通用的collect方法的定义（还有其他override方法）
	 */
	@Test
	public void test1() {
		/***
		 * <R> R collect(<br/>
		 * Supplier<R> supplier,<br/>
		 * BiConsumer<R, ? super T> accumulator,<br/>
		 * BiConsumer<R, R> combiner);
		 */
		// 先来看看这三个参数的含义：Supplier supplier是一个工厂函数，用来生成一个新的容器；
		// BiConsumer accumulator也是一个函数，用来把Stream中的元素添加到结果容器中；
		// BiConsumer combiner还是一个函数，用来把中间状态的多个结果容器合并成为一个（并发的时候会用到）。看晕了？来段代码！

		List<Integer> nums = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		List<Integer> numsWithoutNull = //
				nums.stream()//
						.filter(num -> num != null)//
						.collect(//
								ArrayList<Integer>::new, //
								// () -> new ArrayList<Integer>(), //
								ArrayList::add, //
								// (list, item) -> list.add(item), //
								ArrayList::addAll//
				// (list1, list2) -> list1.addAll(list2)//
				);
		System.out.println(numsWithoutNull);
		/***
		 * 从这段代码中从中还可以顺便发现了一个特点<br/>
		 * 函数式接口： BiConsumer<R, R> combiner) 这个可以接受单参数的消费者函数。<br/>
		 * 为什么呢？这样做很合理，只是不知道底层如何实现的。<br/>
		 * 因为这样做意味着可以将调用对象当成第一参数，将给定函数的参数作为第二参数执行。
		 */
	}

	// <R, A> R collect(Collector<? super T, A, R> collector);
	/***
	 * 这个方法使用一个封装的Collector简化参数为一个。
	 * 
	 * Collector接口提供俩个static类型的of方法用于构造Collector。==》自己研究
	 * 
	 * Collectors工具类提供了很多常用的Collector，所以本质上之所以这种方法简单是因为常用的Collector都可以直接获取，而不用自己定义了。
	 */

	// 下面是Collectors的文档注释。
	/**
	 * Implementations of Collector that implement various useful reduction
	 * operations, such as accumulating elements into collections, summarizing
	 * elements according to various criteria, etc.
	 *
	 * <p>
	 * The following are examples of using the predefined collectors to perform
	 * common mutable reduction tasks:
	 *
	 * <pre>
	 * {@code
	 *     // Accumulate names into a List
	 *     List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());
	 *
	 *     // Accumulate names into a TreeSet
	 *     Set<String> set = people.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));
	 *
	 *     // Convert elements to strings and concatenate them, separated by commas
	 *     String joined = things.stream()
	 *                           .map(Object::toString)
	 *                           .collect(Collectors.joining(", "));
	 *
	 *     // Compute sum of salaries of employee
	 *     int total = employees.stream()
	 *                          .collect(Collectors.summingInt(Employee::getSalary)));
	 *
	 *     // Group employees by department
	 *     Map<Department, List<Employee>> byDept
	 *         = employees.stream()
	 *                    .collect(Collectors.groupingBy(Employee::getDepartment));
	 *
	 *     // Compute sum of salaries by department
	 *     Map<Department, Integer> totalByDept
	 *         = employees.stream()
	 *                    .collect(Collectors.groupingBy(Employee::getDepartment,
	 *                                                   Collectors.summingInt(Employee::getSalary)));
	 *
	 *     // Partition students into passing and failing
	 *     Map<Boolean, List<Student>> passingFailing =
	 *         students.stream()
	 *                 .collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));
	 *
	 * }
	 * </pre>
	 *
	 * @since 1.8
	 */
	@Test
	public void test2() {
		// prepare data
		List<Person> people = new ArrayList<>();
		people.add(new Person("tom", 20, "basketball"));
		people.add(new Person("alice", 22, "football"));
		people.add(new Person("eve", 22, "running"));
		people.add(new Person("david", 19, "swimming"));

		// Accumulate names into a List
		List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());
		System.out.println(list);

		// Accumulate names into a TreeSet
		Set<String> set = people.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));
		System.out.println(set);
		// Convert elements to strings and concatenate them, separated by commas

		String joined = people.stream().map(Object::toString).collect(Collectors.joining(", "));
		/***
		 * <pre>
		 * {
		 * 	&#64;code
		 * 	String joined2 = people.stream().map(StringUtils::toString).collect(Collectors.joining(","));
		 * }
		 * </pre>
		 * 
		 */
		// 上边注释这句报错如下 --> 一个类的静态方法才能作为函数式接口的实例？
		// Cannot make a static reference to the non-static method
		// toString(Object) from the type StringUtils
		// 可是Object::toString也不是静态方法啊，为什么可以呢？
		// 根据实际需求其实很好想通，这个编译器应该有动态推测。使用非静态方法，则stream的元素会被作为调用者（也算个参数）。
		// 所以本身map是接收一个一元Function接口实例。
		// 如果传入一个非静态方法，应该是一个空参数方法即可。
		// 如果传入一个静态方法，则应该是一个带单一参数的方法。且这个参数的类型和stream元素类型一致。
		// 比如下面这句还是错误的，虽然使用了静态方法，但是却类型不一样。
		/***
		 * <pre>
		 * {
		 * 	&#64;code
		 * 	String joined2 = people.stream().map(StringUtils::toString_static_2).collect(Collectors.joining(","));
		 * }
		 * </pre>
		 */
		// 报错如下：
		// The type StringUtils does not define toString_static_2(Person) that
		// is applicable here
		// 正确如下:
		String joined2 = people.stream().map(StringUtils::toString_static).collect(Collectors.joining(","));
		System.out.println(joined);
		System.out.println(joined2);

		// Compute sum of salaries of people
		int total = people.stream().collect(Collectors.summingInt(Person::getSalary));
		System.out.println(total);

		// Group people by age
		Map<Integer, List<Person>> byAge = people.stream().collect(Collectors.groupingBy(Person::getAge));
		System.out.println(byAge);

		// Compute sum of salaries by age
		Map<Integer, Integer> totalByAge = people.stream()
				.collect(Collectors.groupingBy(Person::getAge, Collectors.summingInt(Person::getSalary)));
		System.out.println(totalByAge);

		// Partition students into passing and failing

		Map<Boolean, List<Person>> partitionByAge = people.stream()
				.collect(Collectors.partitioningBy(s -> s.getAge() >= 21));
		System.out.println(partitionByAge);

		// 自行看看Collectors类可以发现太多方法了，介绍不过来。
		// 而且实现应该还是比较复杂的，不推荐看。想想就好。
	}

	// 其他汇聚
	// reduce方法：reduce方法非常的通用，后面介绍的count，sum等都可以使用其实现。reduce方法有三个override的方法.

	// 1 Optional<T> reduce(BinaryOperator<T> accumulator);
	// 2 T reduce(T identity, BinaryOperator<T> accumulator);
	/***
	 * 原型1和原型2类似，只是少一个T identity 参数。<br/>
	 * 原型1情况下，返回的是Optional类型，这是因为stream中可能没有元素，这就可能导致返回null。所以使用Optional。
	 * 原型2则不需要，因为有一个T类型的identity存在。
	 */

	/***
	 * 3<br/>
	 * 原型3更加复杂些。<br/>
	 * 参数1是 U 类型的identity。不同于stream的T类型。<br/>
	 * 参数2是accumulator函数，因为identity是U类型，所以二元的accumulator的参数是一个U和一个T。结果是U类型。<br/>
	 * 参数3是combiner，是一个二元操作符，也就是俩参数以及输出类型一致（都为U）的二元函数<br/>
	 * 
	 * 这个是为什么呢？其实accumulator就是从最初的identity中开始累积T值的，然后得到一个结果。<br/>
	 * 如果是并行运行，那么最终的多个U类型结果就需要使用combiner进行合并。
	 * 
	 * 需要注意的是，注释中总会提示如下这句话：(这一这个特点不仅仅原型3有，都要满足哦) <br/>
	 * 
	 * combiner.apply(u, accumulator.apply(identity, t))==accumulator.apply(u,t)
	 * 这句话仔细理解好像有点奇怪，有点不合理，为什么要这么奇葩的需要呢？<br/>
	 * 一旦解释：比如我们当前有个整数类型的List，需要求解5*list[0]*list[1]*list[2]*...*list[last_index]。<br/>
	 * 这种情况比较特殊的就是我们有个5要乘。那么如果使用这个reduce的原型，identity就应该是5.<br/>
	 * accumulator和combiner都是整数的乘法。<br/>
	 * 然而这么做实际是有问题的。因为不满足 ( u * identity * t ) == ( u * t ).<br/>
	 * 但是看起来却很合理，为什么不满足就不可以呢？<br/>
	 * 我们不要忘记一点，就是并行处理的情况。<br/>
	 * 如果list被分为俩组进行处理，那么每组中都会被乘一个5，最终合并之后实际就是乘了25.<br/>
	 * 如果是三組，就是125.这个是不确定的值。<br/>
	 * 下面的test3对这个问题进行了测试和验证哦。
	 * 
	 * 原型1和2中同意要满足这个特点，原型1中没有identity，所以没有这个问题<br/>
	 * 原型2中呢，要满足:
	 * 
	 * <pre>
	 * {@code
	 * accumulator.apply(t1, accumulator.apply(identity, t2))==accumulator.apply(t1,t2)}
	 * </pre>
	 * 
	 * 本质就是accumulator.apply(identity, t) == t.
	 * 
	 * 此处说明一下原型1,2和3的区别。<br/>
	 * 原型3的参数2和参数3，一个是二元函数 (U,T)->U，另一个是二元操作符(U,U)->U。<br/>
	 * 如果T==U的话，参数2和参数3就是同一回事了。<br/>
	 * 所以实际上原型2是原型3的特例（U==T的情况），原型2中，accumulator同时充当了combiner。
	 * 
	 * <pre>
	 * {@code
	 *	<U> U reduce(	U identity,
	 *           		BiFunction<U, ? super T, U> accumulator,
	 *          		BinaryOperator<U> combiner
	 * 				);}
	 * </pre>
	 */
	@Test
	public void test3() {
		Integer result1 = Arrays.stream(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })//
				.reduce(//
						1, // 如果此处指定是1，那么不论是否并行结果都是：3628800
						((a, b) -> (a * b)), //
						((a, b) -> (a * b))//
		);
		Integer result2 = Arrays.stream(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })//
				.reduce(//
						2, // 如果此处指定是1，那么不论是否并行结果都是：7257600
						((a, b) -> (a * b)), //
						((a, b) -> (a * b))//
		);
		System.out.println(result1);// 3628800
		System.out.println(result2);// 7257600

		// result2是result1的2倍，这很正常。
		Integer result1_p = Arrays.stream(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })//
				.parallel()//
				.reduce(//
						1, // 如果此处指定是1，那么不论是否并行结果都是：3628800
						((a, b) -> (a * b)), //
						((a, b) -> (a * b))//
		);
		Integer result2_p = Arrays.stream(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })//
				.parallel()//
				.reduce(//
						2, // 如果此处指定是1，那么不论是否并行结果都是：7257600
						((a, b) -> (a * b)), //
						((a, b) -> (a * b))//
		);
		System.out.println(result1_p);// 3628800
		System.out.println(result2_p);// -579076096{ 这个结果非常奇怪，也不管它怎么来的，反正知道原因就好了
										// }
	}

	/***
	 * 下面一旦进行一个collect和reduce的对比。<br/>
	 * 首先这个例子通过collect和reduce实现同意的求和操作。
	 */
	/***
	 * <pre>
	 * {@code
	<R> R collect(	Supplier<R> supplier,
	              	BiConsumer<R, ? super T> accumulator,
	              	BiConsumer<R, R> combiner);
	<U> U reduce(	U identity,
	             	BiFunction<U, ? super T, U> accumulator,
	             	BinaryOperator<U> combiner);
	}
	 * </pre>
	 * 
	 * 如果将R和U看做一样，可以发现其实俩个方法原型很像，唯一区别就是collect使用的而是消费者函数式接口。<br/>
	 * reduce使用的是函数(具体说是二元操作符)-函数式接口。<br/>
	 * 
	 * 因此，从一定角度上来说，这俩者的能力类似，几乎是可以替换彼此的功能。当然区别肯定有，要不然也不会要俩个了。
	 * 
	 */
	@Test
	public void test4() {
		// 通过IntStream的sum直接求和
		int sum1 = Arrays.stream(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })//
				.mapToInt(e -> e)//
				.sum();
		System.out.println(sum1);
		// 通过collect求和[失败]
		int sum2 = Arrays.stream(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })//
				.collect(//
						() -> new Integer(0), // Integer::new不行，因为Integer不存在无参数的构造函数
						(a, b) -> a += b, //
						(a, b) -> a += b//
		);
		System.out.println(sum2);// 失败
		// 失败原因，整数Integer中实在找不到一个方法add。。。
		// 上例中虽然手动实现了a+=b、但a+=b这是这个函数式接口的实现体，我们改变的是形参a。
		// 并没有反应到形参a对应的实参上。

		// 通过reduce求和
		int sum3 = Arrays.stream(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })//
				.reduce(//
						0, (a, b) -> a += b, //
						(a, b) -> a += b//
		);
		System.out.println(sum3);// 成功
		// 注意，我们此处使用了类似Consumer的实例，但实际无所谓，因为a+=b是一个表达式，表达式默认就可以作为一个返回值。
	}

	// 其他Stream方法
	// count
	// – 搜索相关
	// – allMatch：是不是Stream中的所有元素都满足给定的匹配条件
	// – anyMatch：Stream中是否存在任何一个元素满足匹配条件
	// – findFirst: 返回Stream中的第一个元素，如果Stream为空，返回空Optional
	// – noneMatch：是不是Stream中的所有元素都不满足给定的匹配条件
	// – max和min：使用给定的比较器（Operator），返回Stream中的最大|最小值
}
