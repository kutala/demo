package com.kuta.demo.concurrent.lock;

import java.util.concurrent.CyclicBarrier;

/**
 * @notes:公共屏障点 
 * @author: kuta.li
 * @date: 2016年7月29日-下午3:08:29
 * 线程数=屏障点的时候触发所有线程runnable 
 * 线程数<屏障点的时候所有线程等待
 */
public class CyclicBarrierDemo {

    private static CyclicBarrier cyclicBarrier;
    
    public static void main(String[] args) {
        cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Thread1().start();
        }
    }
    
    static class Thread1 extends Thread{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "达到……");
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "完成……");
        }
        
    }
}
