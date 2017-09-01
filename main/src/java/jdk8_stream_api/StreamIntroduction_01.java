package jdk8_stream_api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamIntroduction_01 {

	/***
	 * 本类对Stream接口进行分析。
	 */

	/**
	 * 根据下面的注释大概知道BaseStream就是一个元素序列，支持序列化，并行化的聚集操作。
	 * 
	 * 很奇怪的是Stream中的声明都很奇怪。
	 * 
	 * public interface BaseStream<T, S extends BaseStream<T, S>> extends
	 * AutoCloseable
	 * 
	 * 这个继承的是什么我们不考虑，但这个形式参数就很奇怪。<br/>
	 * 第一个形式参数是元素类型T。<br/>
	 * 第二个形式参数是S，代表了BaseStream的实际实现。<br/>
	 * S既然是BaseStream的实现，所以自然是BaseStream的后代，所以有个限制是 S extends BaseStream<T, S>
	 * 。<br/>
	 * 只是这种声明和需求好像以前很少见过？
	 * 
	 * 通过看BaseStream提供的接口方法的话可以知道这么做的基本目的是，这些接口方法有的需要返回S类型。
	 * 
	 * 举例说明，请看test0()===================================================
	 * 
	 * Base interface for streams, which are sequences of elements supporting
	 * sequential and parallel aggregate operations.
	 *
	 * See the class documentation for {@link Stream} and the package
	 * documentation for <a href="package-summary.html">java.util.stream</a> for
	 * additional specification of streams, stream operations, stream pipelines,
	 * and parallelism, which governs the behavior of all stream types.
	 *
	 * @param <T>
	 *            the type of the stream elements
	 * @param <S>
	 *            the type of of the stream implementing {@code BaseStream}
	 * @see Stream
	 * @see IntStream
	 * @see LongStream
	 * @see DoubleStream
	 * 
	 *      提供了接口方法： Iterator<T> iterator();<br/>
	 *      Spliterator<T> spliterator();<br/>
	 *      S sequential();<br/>
	 *      S parallel();<br/>
	 *      S unordered();<br/>
	 *      S onClose(Runnable closeHandler);<br/>
	 */
	@Test
	public void test0() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");// 0
		list.add("world");// 1
		list.add("i");// 2
		list.add("am");// 3
		list.add("yidan");// 4
		list.add("how");// 5
		list.add("you");// 6
		list.add("you");// 7
		List<String> list2 = list.subList(2, 5);
		// 需要强制转换
		list = (ArrayList<String>) list.subList(2, 5);

		// 并且链式调用也无法调用ArrayList本身的方法。
		System.out.println(list.subList(2, 5));

		/***
		 * 举个例子，List接口，ArrayList是List的实现。<br/>
		 * 然而List中一个方法： List<E> subList(int fromIndex, int toIndex);<br/>
		 * 返回的只能是List，这样导致的上边的代码调用了subList方法之后返回的不是ArrayList。<br/>
		 * 如果有个方法是ArrayList自己有的方法，而不是实现List接口的方法，比如B方法（具体不举例）。<br/>
		 * 那么就必须强制转换了。
		 */

	}

	/***
	 * Stream<T> extends BaseStream<T, Stream<T>> <br/>
	 * 
	 * 接口方法：<br/>
	 * 
	 * Returns a stream consisting of the elements of this stream that match the
	 * given predicate.<br/>
	 * Stream<T> filter(Predicate<? super T> predicate); <br/>
	 * 
	 * Returns a stream consisting of the results of applying the given function
	 * to the elements of this stream. <br/>
	 * <R> Stream<R> map(Function<? super T, ? extends R> mapper); <br/>
	 * 
	 * Returns an {@code IntStream} consisting of the results of applying the
	 * given function to the elements of this stream. <br/>
	 * IntStream mapToInt(ToIntFunction<? super T> mapper); <br/>
	 * 
	 * 类似还有:<br/>
	 * LongStream mapToLong(ToLongFunction<? super T> mapper); <br/>
	 * DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper); <br/>
	 * 
	 * =========>>>>>><br/>
	 * Returns a stream consisting of the results of replacing each element of
	 * this stream with the contents of a mapped stream produced by applying the
	 * provided mapping function to each element. Each mapped stream is
	 * {@link java.util.stream.BaseStream#close() closed} after its contents
	 * have been placed into this stream. (If a mapped stream is {@code null} an
	 * empty stream is used, instead.) <br/>
	 * 
	 * <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>>
	 * mapper); <br/>
	 * 
	 * 类似的还有： <br/>
	 * IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper);
	 * <br/>
	 * LongStream flatMapToLong(Function<? super T, ? extends LongStream>
	 * mapper); <br/>
	 * DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream>
	 * mapper); <br/>
	 * 
	 * Returns a stream consisting of the distinct elements (according to
	 * {@link Object#equals(Object)}) of this stream. <br/>
	 * Stream<T> distinct(); <br/>
	 * 
	 * Stream<T> sorted(); [ 自然排序 ] <br/>
	 * 
	 * Stream<T> sorted(Comparator<? super T> comparator); [ 根据参数comparator排序 ]
	 * <br/>
	 * 
	 * 下面peek方法对流无作用，仅仅是对其元素每个进行一个消费者消费的accept操作<br/>
	 * Returns a stream consisting of the elements of this stream, additionally
	 * performing the provided action on each element as elements are consumed
	 * from the resulting stream. ======>>>><br/>
	 * Stream<T> peek(Consumer<? super T> action); <br/>
	 * 
	 * Returns a stream consisting of the elements of this stream, truncated to
	 * be no longer than {@code maxSize} in length.======>>>><br/>
	 * Stream<T> limit(long maxSize);
	 * 
	 * Returns a stream consisting of the remaining elements of this stream
	 * after discarding the first {@code n} elements of the stream. If this
	 * stream contains fewer than {@code n} elements then an empty stream will
	 * be returned.<br/>
	 * ======>>>><br/>
	 * Stream<T> skip(long n);
	 * 
	 * 一旦觉得下面这个方法和peek方法实际作用类似，区别是peek返回stream可以继续操作，forEach则无返回。<br/>
	 * Performs an action for each element of this stream.<br/>
	 * ======>>>><br/>
	 * void forEach(Consumer<? super T> action);
	 * 
	 * Performs an action for each element of this stream, in the encounter
	 * order of the stream if the stream has a defined encounter order.<br/>
	 * ======>>>><br/>
	 * void forEachOrdered(Consumer<? super T> action);
	 * 
	 * Returns an array containing the elements of this stream.<br/>
	 * ======>>>><br/>
	 * Object[] toArray(); <br/>
	 * 
	 * 这个方法类似于上一个，只是提供一个generator，根据方法描述得知目的大概是为了改变返回的数组的类型。<br/>
	 * 这个类型不同于Stream中的T类型，然而也不完全不同（否则就用map函数了），返回的是T的super类型。 ======>>>><br/>
	 * <A> A[] toArray(IntFunction<A[]> generator);
	 * 
	 * <br/>
	 * ======>>>><br/>
	 */

	/**
	 * Performs a reduction on the elements of this stream, using the provided
	 * identity value and an associative accumulation function, and returns the
	 * reduced value. This is equivalent to:
	 * 
	 * <pre>
	 * {@code
	 *     T result = identity;
	 *     for (T element : this stream)
	 *         result = accumulator.apply(result, element)
	 *     return result;
	 * }
	 * </pre>
	 * 
	 * but is not constrained to execute sequentially.
	 *
	 * @apiNote Sum, min, max, average, and string concatenation are all special
	 *          cases of reduction. Summing a stream of numbers can be expressed
	 *          as:
	 *
	 *          <pre>
	 * {@code
	 *     Integer sum = integers.reduce(0, (a, b) -> a+b);
	 * }
	 *          </pre>
	 *
	 *          or:
	 *
	 *          <pre>
	 * {@code
	 *     Integer sum = integers.reduce(0, Integer::sum);
	 * }
	 *          </pre>
	 *
	 *          <p>
	 *          While this may seem a more roundabout way to perform an
	 *          aggregation compared to simply mutating a running total in a
	 *          loop, reduction operations parallelize more gracefully, without
	 *          needing additional synchronization and with greatly reduced risk
	 *          of data races.
	 *
	 * @param identity
	 *            the identity value for the accumulating function
	 * @param accumulator
	 *            an
	 *            <a href="package-summary.html#Associativity">associative</a>,
	 *            <a href=
	 *            "package-summary.html#NonInterference">non-interfering</a>,
	 *            <a href="package-summary.html#Statelessness">stateless</a>
	 *            function for combining two values
	 * @return the result of the reduction
	 */
	// T reduce(T identity, BinaryOperator<T> accumulator);

	// 这个方法和上一个reduce类似，只是不需要初始值，以第一个元素为初始值即可。
	// 同时因为可能无元素，避免返回null，所以使用了Optional。
	// Optional<T> reduce(BinaryOperator<T> accumulator);

	/***
	 * 这里顺便介绍一下Optional的用法。<br/>
	 * 看源码的话其实Optional的实现非常简单。就是提供了null和非null俩种情况的封装而已。<br/>
	 * Optional<T>想要转换成T可以通过:<br/>
	 * 1: public T orElse(T other);<br/>
	 * 2: public T orElseGet(Supplier<? extends T> other);<br/>
	 * 
	 * 那么为什么我们需要Optional呢，它又如何解决了null问题呢？<br/>
	 * 其实从本质上它也解决不了啥，null问题是程序设计的逻辑问题，或者数据的异常情况，本身存在就是合理的。<br/>
	 * 重点是检测到如何对应解决而已。<br/>
	 * 
	 * 然后这里遇到一个问题就是，假设有个情况是链式调用，如果其中一步骤返回null，那么后边的调用就会报空指针异常。<br/>
	 * 所以我们设计程序可能就需要每个步骤判断一次是否null。这就很麻烦了。本身使用链式调用就是为了代码紧凑，现在不能用？<br/>
	 * 
	 * 这个得看情况，有些情况，对于null的解决是可以忽略并且继续的时候[比如null和empty的意义类似]，Optional的作用就来了。<br/>
	 * 什么意思呢？ 比如Stream中有一堆元素，其中一个null，我们希望null返回null，对其他元素进行处理即可。
	 * 可是如果直接用可能报错，但如果把Stream元素换成Optional类型就可以了。
	 */
	// 下面是Optional提供的map方法，看看就懂了哦哦。
	// public<U> Optional<U> map(Function<? super T, ? extends U> mapper) {
	// Objects.requireNonNull(mapper);
	// if (!isPresent())
	// return empty();
	// else {
	// return Optional.ofNullable(mapper.apply(value));
	// }
	// }

	// 下面这个方法比较复杂，自己看注释吧，不是特别好理解这个combiner的用处，需要找到具体用法才知道。
	// 可以看看test1就懂了。
	// 其实就是典型的map-reduce，因为本身map-reduce涉及到并行处理。
	// 此处的combine就充当第二次reduce的方式。
	// 这个函数不好举例，可以看看test1，是下面另一个函数的举例，因为那个是consumer，目前本人一下写不出函数的情况的例子.
	// <U> U reduce(U identity,
	// BiFunction<U, ? super T, U> accumulator,
	// BinaryOperator<U> combiner);

	// R是通过第一个参数指定的返回结果。
	// 第二个参数是用于reduce操作的。
	// 参数三就是reduce。

	// <R> R collect(Supplier<R> supplier,
	// BiConsumer<R, ? super T> accumulator,
	// BiConsumer<R, R> combiner);
	@Test
	public void test1() {
		Stream<String> stream = Stream.of("hello", "world");
		List<String> list = stream.collect(ArrayList::new, null, ArrayList::addAll);

		System.out.println(list);

	}

	// Collector可以理解成一个封装对上边的几种方式。
	// <R, A> R collect(Collector<? super T, A, R> collector);
	// Optional<T> min(Comparator<? super T> comparator);
	// Optional<T> max(Comparator<? super T> comparator);
	// long count();
	// boolean anyMatch(Predicate<? super T> predicate);
	// boolean allMatch(Predicate<? super T> predicate);
	// boolean noneMatch(Predicate<? super T> predicate);
	// Optional<T> findFirst();
	// Optional<T> findAny();

	// 通过查看继承结构可以发现
	// BaseStream接口的子接口还有很多
	// IntStream,LongStream,DoubleStream
	// 这三个是针对基本数据类型int，long，double的。

	// 以及一个抽象实现类 AbstractPipeline
	// 也就是说 AbstractPipeline 抽象类实现了 BaseStream 接口

	// Stream 是 BaseStream 的子接口
	// Stream接口则只有一个抽象实现类 ReferencePipeline，并且这个类继承了AbstractPipeline类。
	// 也就是Stream的抽象实现类ReferencePipeline继承了Stream的父接口BaseStream的抽象实现类
	// AbstractPipeline。
	// 很合理哦~

	// ReferencePipeline的具体实现类又有： Head, StateFulOp, StateLessOp
	// 这三个类是在 ReferencePipeline 类文件中static方式定义的。
	// 其中Head是实现类，另外俩个仍然是抽象类。
	
	//这些都是什么呢？看了看源码都比较复杂，推荐可以不看了。
	//基本就是Stream的各种实现。因为之前讲的都是接口，实现方式还是比较复杂的。
	
}
