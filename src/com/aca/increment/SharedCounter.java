package com.aca.increment;

public class SharedCounter {

    private static int counter;

    public void increment() {
        synchronized (this) {
            counter++;
        }
    }

    public static int getCounter() {
        return counter;
    }

}
