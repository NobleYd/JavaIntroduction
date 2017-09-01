package jdk8_lambda_函数式编程接口;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class LambdaTest {

    /***
     * 类型参数e可以自动推测，可以省略。
     */
    @Test
    public void 小试牛刀1() {
        Arrays.asList("a", "b", "d").forEach(e -> System.out.println(e));
        Arrays.asList("a", "b", "d").forEach((String e) -> System.out.println(e));

    }

    /***
     * 多余一行代码可以使用代码块。
     */
    @Test
    public void 小试牛刀2() {
        Arrays.asList("a", "b", "d").forEach(e -> {
            System.out.print(e);
            System.out.print(e);
        });
    }

    /***
     * 多余一行代码可以使用代码块。
     * 
     * 如果只是用一行代码，不使用代码块情况。可以省略return关键字。
     * 
     * 也即是e->f，等价于 e->{return f;}
     * 
     */
    @Test
    public void 小试牛刀3() {
        List<Integer> list1 = Arrays.asList("a", "bc", "d").stream().map(e -> e.length()).collect(Collectors.toList());
        List<Integer> list2 = Arrays.asList("a", "bcc", "dddd").stream().map(e -> {
            return e.length();
        }).collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(list2);
    }

    /***
     * 一旦：从一定程度上可以认为java实现lambda的方式是使用匿名类的方式。
     * 
     * 所以使用到外部变量必须是final类型。
     * 
     * 但是不需要主动声明为final。而是java会自动将非final的那些使用到的变量假设为final，然后之后如果有赋值操作直接编译报错。
     * 
     */
    @Test
    public void 小试牛刀4() {
        String separator = ",";
        Arrays.asList("a", "b", "d").forEach((String e) -> System.out.print(e + separator));
        // 下面这行如果不注释，则java认为separator不是final类型。
        // separator = null;
        // 就会在lambda定义的位置中使用separator的地方报错（编译报错）。
        // Local variable separator defined in an enclosing scope must be final
        // or effectively final
    }

}
