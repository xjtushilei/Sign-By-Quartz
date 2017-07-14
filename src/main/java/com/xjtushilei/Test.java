package com.xjtushilei;

import java.util.Random;

/**
 * @author shilei
 * @Date 2017/7/13.
 */
public class Test {

    public static void main(String[] a) {
        Random random = new Random(System.nanoTime());
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(10));
        }
    }
}
