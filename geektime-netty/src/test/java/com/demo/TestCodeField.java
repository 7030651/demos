package com.demo;

import java.util.concurrent.atomic.AtomicLong;

public class TestCodeField {

    static {
        System.out.println("in static.");
    }

    {
        System.out.println("in normal.");
    }

    public static void main(String[] args) {
        TestCodeField test = new TestCodeField();
        TestCodeField test2 = new TestCodeField();

        /*
         * in static.
         * in normal.
         * in normal.
         */

    }
}
