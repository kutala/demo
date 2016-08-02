package com.kuta.demo.concurrent;


public class Test {

    volatile int a;
    
    public void add(){
        a++;
        System.out.println(a);
    }
    
    
    public static void main(String[] args) throws InterruptedException {
        final Test t = new Test(); 
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    t.add();
                }
            }).start();
        }
        Thread.sleep(3000);
        System.out.println("end#" + t.a);
    }
}
