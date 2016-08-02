package com.kuta.demo.concurrent;

public class VolatileDemo {

    volatile Integer x = 10000;
    
    public static void main(String[] args) {
        final VolatileDemo v = new VolatileDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + ", x = " + v.x);
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + ", x = " + v.x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程1").start();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    v.x = v.x + 10000;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "线程2").start();
    }
}
