package functional_interface;

public class Introduction {

    /***
     * 打开FunctionalInterface。<br/>
     * 然后查找引用。发现jdk8中有71处引用。都是接口。<br/>
     * 也就是都是符合函数式接口的接口被标注了这个注解。
     */
    // 常见的FunctionalInterface如下所示：
    // Runnable
    // java.util.Comparator<T>
    // java.concurrent.Callable<V>
    // java.util.function.*
    //

    /***
     * 下面首先介绍一堆接口（有的不是函数式接口）
     * 
     */
    /***
     * Spliterator<T>
     * 
     */
    /***
     * 消费者接口，接收一个参数，无返回。 <br/>
     * Consumer<T> <br/>
     * Represents an operation that accepts a single input argument and returns no result. Unlike most other functional
     * interfaces, {@code Consumer} is expected to operate via side-effects.
     * 
     * @param <T>
     *            the type of the input to the operation
     */
    /**
     * 俩参数消费者接口，接收俩个参数，无返回。<br/>
     * BiConsumer<T, U> <br/>
     * Represents an operation that accepts two input arguments and returns no result. This is the two-arity
     * specialization of {@link Consumer}. Unlike most other functional interfaces, {@code BiConsumer} is expected to
     * operate via side-effects.
     *
     * @param <T>
     *            the type of the first argument to the operation
     * @param <U>
     *            the type of the second argument to the operation
     */

    /**
     * 函数接口，接收一个输入，有一个返回值。<br/>
     * Function<T, R> <br/>
     * Represents a function that accepts one argument and produces a result.
     *
     * @param <T>
     *            the type of the input to the function
     * @param <R>
     *            the type of the result of the function
     */
    /**
     * 俩元函数，接收俩参数，有一个返回值。[消费者接口和函数接口相对应]<br/>
     * BiFunction<T, U, R><br/>
     * Represents a function that accepts two arguments and produces a result. This is the two-arity specialization of
     * {@link Function}.
     *
     * @param <T>
     *            the type of the first argument to the function
     * @param <U>
     *            the type of the second argument to the function
     * @param <R>
     *            the type of the result of the function
     */
    /***
     * UnaryOperator<T> extends Function<T, T>
     * 
     * 一元操作符，一个输入，一个输出（同类型）。
     * 
     */
    /**
     * 二元操作符，接收同类型的俩输入，返回同类型的一个输出。<br/>
     * BinaryOperator<T> extends BiFunction<T,T,T> <br/>
     * Represents an operation upon two operands of the same type, producing a result of the same type as the operands.
     * This is a specialization of {@link BiFunction} for the case where the operands and the result are all of the same
     * type.
     *
     * @param <T>
     *            the type of the operands and result of the operator
     *
     * @see BiFunction
     * @see UnaryOperator
     */
    /***
     * 预测，其实就是一个一元函数，返回限制为布尔类型。<br/>
     * 个人觉得，jdk有的地方是继承实现，有的地方是单独实现，这个原因不要太纠结，只是一个设计思路而已。<br/>
     * 可能预测这个看起来虽然本质上是函数，但和函数有区别，所以就不一起实现了吧。<br/>
     * 而且Function中有个andThen等方法，虽然本质上不冲突，但从意义上讲Predict类型不存在那种需求。<br/>
     * 
     * 对于预测类型提供的默认方法有and，or，neagte（反）。<br/>
     * 
     * Predicate<T> <br/>
     * Represents a predicate (boolean-valued function) of one argument.
     *
     * @param <T>
     *            the type of the input to the predicate
     *
     * @since 1.8
     */
    /***
     * BiPredicate<T, U> <br/>
     * 二元预测
     */

    /**
     * Supplier<T> <br/>
     * 供给者，这个和消费者Consumer对应。<br/>
     * 消费者是接收输入无输出。<br/>
     * 供给者是无输入，只返回。<br/>
     * 
     * Represents a supplier of results.
     * 
     * @param <T>
     *            the type of results supplied by this supplier
     */

    /***
     * 还有一堆函数不是很特别。
     * 
     * 比如为了基本类型数据的使用，提供的一些。
     * 
     * 比如BooleanSupplier，因为使用Supplier只能使用Boolean类型，而不能使用boolean类型。
     * 
     * 所以类似的其他基本数据类型也都提供了很多 供给者，预测，消费者，函数等接口。
     */

}
