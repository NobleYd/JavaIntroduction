package jdk8_接口的默认和静态方法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
 * Java8用默认方法与静态方法这两个新概念来扩展接口的声明。<br/>
 * 默认方法使接口有点像Traits（Scala中特征(trait)类似于Java中的Interface，<br/>
 * 但它可以包含实现代码，也就是目前Java8新增的功能），但与传统的接口又有些不一样，<br/>
 * 它允许在已有的接口中添加新方法，而同时又保持了与旧版本代码的兼容性。
 * 
 * @author nobleyd
 *
 */
public class InterfaceDefaultStaticMethod {

    private interface Defaulable {
        // Interfaces now allow default methods, the implementer may or
        // may not implement (override) them.
        default String notRequired() {
            return "Default implementation";
        }
    }

    /***
     * Defaulable接口用关键字default声明了一个默认方法notRequired()<br/>
     * Defaulable接口的实现者之一DefaultableImpl实现了这个接口，并且让默认方法保持原样。<br/>
     * Defaulable接口的另一个实现者OverridableImpl用自己的方法覆盖了默认方法。<br/>
     */
    private static class DefaultableImpl implements Defaulable {
    }

    private static class OverridableImpl implements Defaulable {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    /***
     * Java 8带来的另一个有趣的特性是接口可以声明（并且可以提供实现）静态方法。
     */
    private interface DefaulableFactory {
        // Interfaces now allow static methods
        static Defaulable create(Supplier<Defaulable> supplier) {
            return supplier.get();
        }
    }

    // 下面的一小段代码片段把上面的默认方法与静态方法黏合到一起。
    /***
     * ::new貌似是个新用法。
     */
    @org.junit.Test
    public void test0() {

        Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
        System.out.println(defaulable.notRequired());

        defaulable = DefaulableFactory.create(OverridableImpl::new);
        System.out.println(defaulable.notRequired());

    }

    /***
     * 相关的应用
     */
    @org.junit.Test
    public void test1() {
        List<String> list = new ArrayList<String>(Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long"));
        Stream<String> stream = list.stream();
        System.out.println(list);
        Stream<String> stream2 = stream.map(e -> e.toUpperCase());
        List<String> list2 = stream2.collect(Collectors.toList());
        System.out.println(list2);
    }

}
