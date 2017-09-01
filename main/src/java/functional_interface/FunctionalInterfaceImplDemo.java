package functional_interface;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import org.junit.Test;

/***
 * 本次测试准备举例一些函数式接口的实现。<br/>
 * 包括手动实现，以及常见的函数引用举例<br/>
 *
 */
public class FunctionalInterfaceImplDemo {

    /***
     * 首先呢，我们依旧像Introduction中那样对函数式接口进行一个分类。
     * 
     * 然后针对每种每种再举例。
     */

    /***
     * 我们通过参数数量，返回值数量，参数类型，返回类型等方式分类。
     * 
     * 注意，关于参数类型T,U,R等。<br/>
     * 一般情况，单一参数好像是用T。<br/>
     * 俩参数是T和U。<br/>
     * 如果涉及到结果类型，那么结果类型使用R。<br/>
     * 
     * 注意：<br/>
     * 输出最多可能一个，没多个的情况。<br/>
     * 输入一般会考虑0,1,2个的情况。
     * 
     */

    // *: 0参数，0输出。-->无意义

    // 1: 1参数，0输出。-->Consumer<T>; T指定参数1类型
    // 2: 2参数，0输出。-->BiConsumer<T,U>; T,U分别指定参数1与参数2类型

    // 3: 0参数，1输出。-->Supplier<T>; T指定输出类型

    // 4: 1参数，1输出。-->Function<T,R>; T指定参数类型,R指定输出类型
    // 5: 2参数，1输出。-->BiFunction<T,U,R>; T,U分别指定参数1与参数2类型,R指定输出类型
    // 6: 1参数，1输出。-->UnaryOperator<T> extends Function<T, T>; T指定参数和结果类型
    // 7: 2参数，1输出。-->BinaryOperator<T> extends BiFunction<T,T,T>; T指定参数和结果类型

    // 此外还有很多指定其中某些类型为常见类型的接口,这些我们都忽略不考虑分类了。除了Boolean类型。
    // 之所以考虑Boolean,是因为jdk专门为Booelan提供了Predict.
    // 其他的都是类型名字,很容易猜出来,就不讲了.
    // 8: 1参数，1Boolean输出。-->Predict<T>
    // 9: 2参数，1Boolean输出。-->BiPredict<T,U>

    // 分类结束，开始举例。

    /***
     * 有几条规则很重要，牢记。<br/>
     * 1: 0输出的函数式接口--可接受--带输出的函数。<br/>
     * 2: 函数式接口--可接受--静态方法。<br/>
     * 3: 函数式接口--可接受--非静态方法。<br/>
     * 这种情况下有个要求是接口参数1是该非静态方法所属类。因为接口参数1最终将会作为调用该非静态方法的调用者。<br/>
     * 4: <br/>
     * 
     * <pre>
     * {@code
     * 
     * }
     * </pre>
     */

    // 1: 1参数，0输出。-->Consumer<T>; T指定参数1类型
    @Test
    public void test1() {
        Consumer<Integer> consumer = null;
        consumer = Integer::valueOf;// 静态1输入1输出函数
        consumer = Integer::new;// 1输入0输出构造函数

        // 下面这俩个从理论上讲也可以，只是呢，有歧义导致不知道怎么搞。
        // 就是hashCode有俩中形式都符合此处要求，编译器不知道选择哪个。
        // consumer = Integer::toString;
        // consumer = Integer::hashCode;

        // 下面这个小插曲，演示一下动态类型判断，Integer::new在不同的接受变量情况下也是可以的。
        Consumer<String> consumer3 = Integer::new;// 1输入0输出构造函数
    }

    // 2: 2参数，0输出。-->BiConsumer<T,U>; T,U分别指定参数1与参数2类型
    @Test
    public void test2() {
        BiConsumer<Integer, Integer> bc = null;
        bc = Integer::sum;// 静态2输入1输出函数
        bc = Integer::compareTo;// 非静态1输入1输出函数
        bc.accept(1, 2);

        bc = (a, b) -> {
        };

    }

    // 3: 0参数，1输出。-->Supplier<T>; T指定输出类型
    @Test
    public void test3() {
        Supplier<Integer> s = null;
        s = () -> new Integer(1);
        s = () -> {
            return new Integer(1);
        };

    }

    // 4: 1参数，1输出。-->Function<T,R>; T指定参数类型,R指定输出类型
    @Test
    public void test4() {
        Function<Integer, Integer> s = null;
        s = e -> e;
        s = (Integer e) -> 2 * e;
        s = Integer::new;

        Function<Long, Integer> s1 = null;
        s1 = e -> e.intValue();
    }

    // 5: 2参数，1输出。-->BiFunction<T,U,R>; T,U分别指定参数1与参数2类型,R指定输出类型
    @Test
    public void test5() {
        BiFunction<Integer, Integer, Integer> bc = null;
        bc = Integer::sum;// 静态2输入1输出函数
        bc = Integer::compareTo;// 非静态1输入1输出函数
        bc = Integer::compare;// 静态2输入1输出函数
        System.out.println(bc.apply(1, 2));
    }

    // 6: 1参数，1输出。-->UnaryOperator<T> extends Function<T, T>; T指定参数和结果类型
    @Test
    public void test6() {
        UnaryOperator<Integer> op = null;
        op = e -> e;
    }

    // 7: 2参数，1输出。-->BinaryOperator<T> extends BiFunction<T,T,T>; T指定参数和结果类型
    @Test
    public void test7() {
        BinaryOperator<Integer> bOp = null;
        bOp = (a, b) -> a + b;
        bOp = Integer::sum;
        bOp = Integer::compare;
        bOp = Integer::compareTo;
    }

    // 8: 1参数，1Boolean输出。-->Predict<T>
    @Test
    public void test8() {
        Predicate<Integer> p1 = null;
        p1 = e -> e != 1;
        p1 = Objects::isNull;
        p1 = Objects::nonNull;
    }

    // 9: 2参数，1Boolean输出。-->BiPredict<T,U>
    @Test
    public void test9() {
        BiPredicate<String, String> bp = null;
        bp = String::contains;
        bp = String::startsWith;
        bp = String::endsWith;
    }

}
