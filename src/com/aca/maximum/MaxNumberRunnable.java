package com.aca.maximum;

import java.util.List;
import java.util.Random;

import static com.aca.Main.BUTCH_COUNT;
import static com.aca.Main.COUNT;

public class MaxNumberRunnable implements Runnable {

    private Random random = new Random();
    private List list;
    private Integer maxValue;

    MaxNumberRunnable(List list) {
        this.list = list;
    }

    Integer getMaxValue() {
        return maxValue;
    }

    void findMaxValue(){
        maxValue = (Integer) list.get(0);
        for (int i = 1; i < BUTCH_COUNT; i++) {
            if ((Integer) list.get(i) > maxValue){
                maxValue = (Integer) list.get(i);
            }
        }
    }

    public void findMaxValue(List list){
        maxValue = (Integer) list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if ((Integer) list.get(i) > maxValue){
                maxValue = (Integer) list.get(i);
            }
        }
    }

    private void adder(){
        for (int i = 0; i < BUTCH_COUNT; i++) {
            list.add(random.nextInt(COUNT));
        }
    }

    @Override
    public void run() {
        adder();
    }

}
