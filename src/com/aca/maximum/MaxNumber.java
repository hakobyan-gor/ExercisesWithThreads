package com.aca.maximum;

import java.util.ArrayList;
import java.util.List;

import static com.aca.Main.THREAD_COUNT;

public class MaxNumber {

    private List<Thread> threadList = new ArrayList<>();
    private List<MaxNumberRunnable> maxNumberRunnableList = new ArrayList<>();
    private Integer maxValue;

    public void findMaxValueWithThreads() {

        initializeMaxNumberRunnableList();
        initializeThreadList();

        long nanoTime = System.nanoTime();

        startThreads();
        joinThreads();

        maxNumberRunnableList.get(0).findMaxValue();
        Integer maxValue = maxNumberRunnableList.get(0).getMaxValue();
        for (int i = 1; i < THREAD_COUNT; i++) {
            maxNumberRunnableList.get(i).findMaxValue();
            if (maxNumberRunnableList.get(i).getMaxValue() > maxValue) {
                maxValue = maxNumberRunnableList.get(i).getMaxValue();
            }
        }
        System.out.println("Max Value " + maxValue);
        System.out.println((System.nanoTime() - nanoTime) / 1000000000);
    }

    public void findMaxValueWithThreads(List list, int countOfThreads) {

        initializeMaxNumberRunnableList(list, countOfThreads);
        initializeThreadList();

        startThreads();
        joinThreads();

        Integer maxValue = maxNumberRunnableList.get(0).getMaxValue();
        for (int i = 1; i < countOfThreads; i++) {
            if (maxNumberRunnableList.get(i).getMaxValue() > maxValue) {
                maxValue = maxNumberRunnableList.get(i).getMaxValue();
            }
        }
        this.maxValue = maxValue;
        System.out.println("Max Value " + maxValue);

    }

    private void initializeMaxNumberRunnableList(List list, int countOfThreads) {
        for (int i = 0; i < countOfThreads; i++) {
            maxNumberRunnableList.add(new MaxNumberRunnable(list.subList(list.size() / countOfThreads * i, list.size() / countOfThreads * (i + 1))));
            maxNumberRunnableList.get(i).findMaxValue(list.subList(list.size() / countOfThreads * i, list.size() / countOfThreads * (i + 1)));
        }
    }

    private void initializeMaxNumberRunnableList() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            maxNumberRunnableList.add(new MaxNumberRunnable(new ArrayList()));
        }
    }

    private void initializeThreadList() {
        for (MaxNumberRunnable maxNumberRunnable : maxNumberRunnableList) {
            threadList.add(new Thread(maxNumberRunnable));
        }
    }

    private void startThreads() {
        for (Thread thread : threadList) {
            thread.start();
        }
    }

    private void joinThreads() {
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getMaxValue() {
        return maxValue;
    }
}
