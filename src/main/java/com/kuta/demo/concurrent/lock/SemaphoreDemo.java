package com.kuta.demo.concurrent.lock;

import java.util.concurrent.Semaphore;

/**
 * @notes:共享锁   lock是一种独享锁 
 * @author: kuta.li
 * @date: 2016年7月28日-下午1:41:18
 * Semaphore 在初始化的时候可以设置最大的线程数来同时访问共享锁
 */
public class SemaphoreDemo {

    private final Semaphore semaphore;   //声明信号量  
    
    public SemaphoreDemo(){  
        semaphore = new Semaphore(1);  
    }  
    
    public static void main(String[] args) {  
        Thread[] threads = new Thread[10];  
          
        SemaphoreDemo printQueue = new SemaphoreDemo();  
          
        for(int i = 0 ; i < 10 ; i++){  
            threads[i] = new Thread(new Job_(printQueue),"Thread_" + i);  
        }  
          
        for(int i = 0 ; i < 10 ; i++){  
            threads[i].start();  
        }  
    }  
      
    public void printJob(Object document){  
        try {  
            semaphore.acquire();//调用acquire获取信号量  
            long duration = (long) (Math.random() * 10);  
            System.out.println( Thread.currentThread().getName() +   
                    " PrintQueue : Printing a job during " + duration);  
            Thread.sleep(duration);  
            System.out.println(Thread.currentThread().getName() + " the document has bean printed");  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  finally{  
            semaphore.release();  //释放信号量  
        }  
    }  
}

class Job_ implements Runnable{  
    private SemaphoreDemo printQueue;  
      
    public Job_(SemaphoreDemo printQueue){  
        this.printQueue = printQueue;  
    }  
      
    @Override  
    public void run() {  
        System.out.println(Thread.currentThread().getName() + " Going to print a job");  
        printQueue.printJob(new Object());  
    }  
}  