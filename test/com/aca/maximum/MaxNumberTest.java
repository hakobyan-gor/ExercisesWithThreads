package com.aca.maximum;

import java.util.ArrayList;

public class MaxNumberTest {

    public static void main(String[] args) {
        test();
    }

    static void test() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(100);
        arrayList.add(1000);
        arrayList.add(5600);
        arrayList.add(1250);
        arrayList.add(2);
        arrayList.add(18800);
        arrayList.add(1441);
        arrayList.add(186);
        arrayList.add(123);
        arrayList.add(1562);
        arrayList.add(-25);
        arrayList.add(-100);
        arrayList.add(-9100);
        arrayList.add(1520);
        arrayList.add(106245);
        arrayList.add(1005225);
        arrayList.add(100);
        MaxNumber maxNumber = new MaxNumber();
        maxNumber.findMaxValueWithThreads(arrayList, 1);
        if (!maxNumber.getMaxValue().equals(arrayList.get(15))) {
            throw new RuntimeException("Error!");
        }
    }

}
