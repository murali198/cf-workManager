package com.murali.cocurrency.cf;

public class Task {

    private final int duration;
    private final int num;

    public Task(int duration, int num) {
        this.duration = duration;
        this.num = num;
    }
    public String calculate() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(duration * 1000);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Thread.currentThread().getName()+ " - "+ num;
    }
}
