package com.fk.test.motanserver;

import org.junit.Test;

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
}
