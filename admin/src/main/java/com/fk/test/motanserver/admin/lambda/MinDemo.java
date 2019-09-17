package com.fk.test.motanserver.admin.lambda;

import java.text.DecimalFormat;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * @Author: fengk
 * @Description
 * @Date: Create in 16:02 2019/9/4.
 * @Modified By:
 */
public class MinDemo {

    public static void main(String[] args) {

        MinDemo minDemo = new MinDemo();
        int[] nums = {35, 65, -55, 100, -676, 95};

        //函数式编程获取最小值,多线程并行处理
        int min = IntStream.of(nums).parallel().min().getAsInt();
        //新建线程完成简单任务
        Object target2 = (Runnable) () -> System.out.println("新建了一个线程" + min);
        Runnable target3 = () -> System.out.println("新建了一个线程");
        new Thread((Runnable) target2).start();

        Interface1 i1 = (i) -> i * 2;

        Interface1.sub(10, 3);
        System.out.println(i1.add(3, 7));
        System.out.println(i1.doubleNum(20));
        Money money = new Money(999999999);
        Function<Integer, String> moneyFormat = i -> new DecimalFormat("#,###")
                .format(i);
        money.printMoney(moneyFormat);

    }


}

class Money {

    private final int money;

    public Money(int money) {
        this.money = money;
    }

    public void printMoney(Function<Integer, String> moneyFormat) {
        System.out.println("我的存款" + moneyFormat.apply(this.money));
    }
}
