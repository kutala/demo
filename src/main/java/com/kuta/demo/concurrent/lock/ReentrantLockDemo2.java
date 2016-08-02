package com.kuta.demo.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo2 {

    public static void main(String[] args) throws InterruptedException {  
        PrintQueue printQueue = new PrintQueue();  
          
        Thread thread[]=new Thread[10];  
        for (int i = 0; i < 10; i++) {  
            thread[i] = new Thread(new Job(printQueue), "Thread " + i);  
        }  
          
        for(int i = 0 ; i < 10 ; i++){  
            thread[i].start();  
        }  
    }  
}


class PrintQueue {
//    公平锁   许多线程访问时表现为很低的总体吞吐量
//    private final Lock queueLock = new ReentrantLock();
    private final Lock queueLock = new ReentrantLock(true);

    public void printJob(Object document) {
        try {
            queueLock.lock();
            System.out.println(Thread.currentThread().getName() + ": Going to print a document");
            Long duration = (long) (Math.random() * 1000);
            System.out.println(Thread.currentThread().getName() + ":PrintQueue: Printing a Job during " + (duration / 1000) + " seconds");
            Thread.sleep(duration);
            System.out.printf(Thread.currentThread().getName() + ": The document has been printed\n");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }
}


class Job implements Runnable {

    private PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        printQueue.printJob(new Object());
    }
}
