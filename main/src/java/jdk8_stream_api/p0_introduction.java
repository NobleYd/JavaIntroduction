package jdk8_stream_api;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;


public class p0_introduction {
	// 状态
	private enum Status {
		OPEN, CLOSED
	};

	//任务
	private static final class Task {
		// 任务状态
		private final Status status;
		// 任务分数
		private final Integer points;

		// 构造函数
		Task(final Status status, final Integer points) {
			this.status = status;
			this.points = points;
		}

		// get
		public Integer getPoints() {
			return points;
		}

		// get
		public Status getStatus() {
			return status;
		}

		@Override
		public String toString() {
			return String.format("[%s, %d]", status, points);
		}
	}

	@Test
	public void test0() {
		final Collection<Task> tasks = Arrays.asList(//
				new Task(Status.OPEN, 5), //
				new Task(Status.OPEN, 13), //
				new Task(Status.CLOSED, 8) //
		);
		/***
		 * 我们下面要讨论的第一个问题是所有状态为OPEN的任务一共有多少分数？<br/>
		 * 在Java 8以前，一般的解决方式用foreach循环，<br/>
		 * 但是在Java 8里面我们可以使用stream：一串支持连续、并行聚集操作的元素。
		 */
		// Calculate total points of all active tasks using sum()
		final long totalPointsOfOpenTasks = tasks//
				.stream()//
				.filter(task -> task.getStatus() == Status.OPEN)//
				.mapToInt(Task::getPoints)//
				.sum();
		System.out.println(totalPointsOfOpenTasks);

		final long totalPointsOfOpenTasks2 = tasks//
				.stream()//
				.filter(e -> Status.OPEN.equals(e.getStatus()))//
				.map(e -> e.getPoints())//
				.reduce(0, Integer::sum);
		System.out.println(totalPointsOfOpenTasks2);

		/***
		 * 这里有几个注意事项。<br/>
		 * 第一，task集合被转换化为其相应的stream表示。<br/>
		 * 然后，filter操作过滤掉状态为CLOSED的task。<br/>
		 * 下一步，mapToInt操作通过Task::getPoints这种方式调用每个task实例的getPoints方法把Task的stream转化为Integer的stream。<br/>
		 * 最后，用sum函数把所有的分数加起来，得到最终的结果。<br/>
		 * 
		 * 在继续讲解下面的例子之前，关于stream有一些需要注意的地方（详情在这里）.<br/>
		 * stream操作被分成了中间操作与最终操作这两种。<br/>
		 * 中间操作返回一个新的stream对象。中间操作总是采用惰性求值方式，<br/>
		 * 运行一个像filter这样的中间操作实际上没有进行任何过滤，<br/>
		 * 相反它在遍历元素时会产生了一个新的stream对象，<br/>
		 * 这个新的stream对象包含原始stream中符合给定谓词的所有元素。<br/>
		 * 像forEach、sum这样的最终操作可能直接遍历stream，产生一个结果或副作用。<br/>
		 * 当最终操作执行结束之后，stream管道被认为已经被消耗了，没有可能再被使用了。<br/>
		 * 在大多数情况下，最终操作都是采用及早求值方式，及早完成底层数据源的遍历。<br/>
		 */
		// 并行处理
		// Calculate total points of all tasks
		final double totalPoints = tasks//
				.stream()//
				.parallel()//
				.map(task -> task.getPoints()) //
				.reduce(0, Integer::sum);

		System.out.println("Total points (all tasks): " + totalPoints);

		/***
		 * 经常会有这个一个需求：我们需要按照某种准则来对集合中的元素进行分组。<br/>
		 * Stream也可以处理这样的需求，下面是一个例子：
		 */
		// Group tasks by their status
		final Map<Status, List<Task>> map = tasks//
				.stream()//
				.collect(Collectors.groupingBy(Task::getStatus));

		System.out.println(map);

		// 让我们来计算整个集合中每个task分数（或权重）的平均值来结束task的例子。
		final Collection<String> result = tasks.stream() // Stream< String >
				.mapToInt(Task::getPoints) // IntStream
				.asLongStream() // LongStream
				.mapToDouble(points -> points / totalPoints) // DoubleStream
				.boxed() // Stream< Double >
				.mapToLong(weigth -> (long) (weigth * 100)) // LongStream
				.mapToObj(percentage -> percentage + "%") // Stream< String>
				.collect(Collectors.toList()); // List< String >
		System.out.println(result);
	}

}