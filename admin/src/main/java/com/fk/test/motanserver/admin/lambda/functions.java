package com.fk.test.motanserver.admin.lambda;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

/**
 * @Author: fengk
 * @Description
 * @Date: Create in 18:06 2019/9/4.
 * @Modified By:
 */
public class functions {

    @Test
    public void predicate() {
        //断言函数接口
        IntPredicate predicate = i -> i > 0;
        System.out.println(predicate.test(10));

        //消费函数接口
        Consumer<String> consumer = i -> System.out.println(i);
        consumer.accept("请输入数据");
    }

    public static void main(String[] args) {

        Dog dog = new Dog();
        dog.eat(3);
        //方法引用
        Consumer<String> consumer = System.out::println;
        consumer.accept("接受数据");
        //静态方法引用
        Consumer<Dog> dogConsumer = Dog::bark;
        dogConsumer.accept(dog);
        //非静态方法，使用对象实例的引用(一元函数，输入输出类型相同)
        IntUnaryOperator function = dog::eat;
//        dog = null; 引用对象置空不影响函数执行
        System.out.println(function.applyAsInt(10));
        // 使用类名来方法引用(两个输入的函数)
         BiFunction<Dog, Integer, Integer> eatFunction = Dog::eat;
         System.out.println("还剩下" + eatFunction.apply(dog, 2) + "斤");
         //构造方法函数的引用 supplier返回一个数据
        Supplier<Dog> supplier = Dog::new;
        System.out.println(supplier.get());
        // 测试java变量是传值还是传引用即值传递
        List<String> list = new ArrayList<>();
        test(list);
        System.err.println(list);


    }

    private static void test(List<String> list) {
        list = null;
    }

}

class Dog {
    private String name = "哮天犬";

    /**
     * 默认10斤狗粮
     */
    private int food = 10;

    public Dog() {

    }

    /**
     * 带参数的构造函数
     *
     * @param name
     */
    public Dog(String name) {
        this.name = name;
    }

    /**
     * 狗叫，静态方法
     *
     * @param dog
     */
    public static void bark(Dog dog) {
        System.out.println(dog + "叫了");
    }

    /**
     * 吃狗粮 JDK
     * <p>
     * 默认会把当前实例传入到非静态方法，参数名为this，位置是第一个；
     *
     * @param num
     * @return 还剩下多少斤
     */
    public int eat(int num) {
        System.out.println("吃了" + num + "斤狗粮");
        this.food -= num;
        return this.food;
    }

    @Override
    public String toString() {
        return this.name;
    }
}


