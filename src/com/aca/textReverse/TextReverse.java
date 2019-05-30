package com.aca.textReverse;

import java.util.ArrayList;
import java.util.List;

import static com.aca.Main.*;

public class TextReverse {

    private List<Thread> threadList = new ArrayList<>();
    private List<TextReverseRunnable> textReverseRunnableList = new ArrayList<>();
    private StringBuffer randomString = new StringBuffer();
    private StringBuffer reversedString = new StringBuffer();

    private void randomAlphaNumeric() {
        int count = COUNT;
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            randomString.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
    }

    public void reverseString() {

        initializeTextReverseRunnableList();
        initializeThreadList();
        randomAlphaNumeric();

        System.out.println(randomString);

        long now = System.nanoTime();

        startThreads();
        joinThreads();

        StringBuffer reversedStringBuffer = new StringBuffer();
        for (int i = 0; i < BUTCH_COUNT; i++) {
            reversedStringBuffer.append(textReverseRunnableList.get(i).getReversedString());
        }
        System.out.println(reversedStringBuffer);

        System.out.println(System.nanoTime() - now);

    }

    public void reverseString(String string, int countOfThreads) {

        initializeTextReverseRunnableList(string, countOfThreads);
        initializeThreadList();

        System.out.println(string);

        long now = System.nanoTime();

        startThreads();
        joinThreads();

        StringBuffer reversedStringBuffer = new StringBuffer();
        for (int i = countOfThreads - 1; i >= 0; i--) {
            reversedStringBuffer.append(textReverseRunnableList.get(i).getReversedString());
        }
        System.out.println(reversedStringBuffer);
        this.reversedString = reversedStringBuffer;

        System.out.println(System.nanoTime() - now);

    }

    private void initializeTextReverseRunnableList(String string, int countOfThreads) {
        if (countOfThreads == 1 ){
            textReverseRunnableList.add(new TextReverseRunnable(string));
        } else {
            for (int i = 0; i < countOfThreads; i++) {
                textReverseRunnableList.add(new TextReverseRunnable(string.substring(string.length() / countOfThreads * i, string.length() / countOfThreads * (i + 1))));
            }
        }
    }

    private void initializeTextReverseRunnableList() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            textReverseRunnableList.add(new TextReverseRunnable(randomString.substring(BUTCH_COUNT * i, BUTCH_COUNT * (i + 1))));
        }
    }

    private void initializeThreadList() {
        for (TextReverseRunnable textReverseRunnable : textReverseRunnableList) {
            threadList.add(new Thread(textReverseRunnable));
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

    public StringBuffer getReversedString() {
        return reversedString;
    }
}
