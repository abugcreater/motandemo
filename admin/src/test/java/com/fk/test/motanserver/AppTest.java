package com.fk.test.motanserver;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.util.Base64;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }


    public static void main(String[] args) {

        try {
            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (int i = 5; i >= 0; i--) {
                final int j = i;
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        int f = 14 / j;
                    }
                });
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test(){

        byte[]a = "5aaC5p6c5L2g5ZKM5oiR5Lus5LiA5qC354Ot54ix57yW56CB77yM55e06L+35LqO5bel56iL5oqA5pyv77yM6K+35YaZ5LiA5bCB6YKu5Lu277yM566A5Y2V5LuL57uN5L2g6Ieq5bex77yM6Z2e5bi45pyf5b6F5pS25Yiw5L2g55qE5p2l5L+hOiBmZWlqaWxiakBnbWFpbC5jb20KSGFwcHkgY29kaW5nIQ==".getBytes();

        System.out.println( Base64Utils.decode(a).toString());

//        String s = new String(Base64.getDecoder().decode("5aaC5p6c5L2g5ZKM5oiR5Lus5LiA5qC354Ot54ix57yW56CB77yM55e06L+35LqO5bel56iL5oqA5pyv77yM6K+35YaZ5LiA5bCB6YKu5Lu277yM566A5Y2V5LuL57uN5L2g6Ieq5bex77yM6Z2e5bi45pyf5b6F5pS25Yiw5L2g55qE5p2l5L+hOiBmZWlqaWxiakBnbWFpbC5jb20KSGFwcHkgY29kaW5nIQ=="),"UTF-8");

        System.out.println(JSON.toJSON( Base64.getDecoder().decode("5aaC5p6c5L2g5ZKM5oiR5Lus5LiA5qC354Ot54ix57yW56CB77yM55e06L+35LqO5bel56iL5oqA5pyv77yM6K+35YaZ5LiA5bCB6YKu5Lu277yM566A5Y2V5LuL57uN5L2g6Ieq5bex77yM6Z2e5bi45pyf5b6F5pS25Yiw5L2g55qE5p2l5L+hOiBmZWlqaWxiakBnbWFpbC5jb20KSGFwcHkgY29kaW5nIQ==")));
    }

}
