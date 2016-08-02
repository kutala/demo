package com.kuta.demo.concurrent.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @notes:读写锁 
 * @author: kuta.li
 * @date: 2016年7月28日-下午2:55:40
 * 读写锁实现了ReadWriteLock接口、ReadWriteLock接口并不是lock的子接口
 * 读写锁本质是一个锁
 * 读写锁允许同时多个线程拥有读锁、允许单个线程拥有写锁、不允许同时存在读写锁2个线程
 */
public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {
        PricesInfo pricesInfo = new PricesInfo();

        Reader[] readers = new Reader[5];
        Thread[] readerThread = new Thread[5];
        for (int i = 0; i < 5; i++) {
            readers[i] = new Reader(pricesInfo);
            readerThread[i] = new Thread(readers[i]);
        }

        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);

        for (int i = 0; i < 5; i++) {
            readerThread[i].start();
        }
        threadWriter.start();
    }
}


class PricesInfo {
    private double price1;
    private double price2;

    private ReadWriteLock lock;

    public PricesInfo() {
        price1 = 1.0;
        price2 = 2.0;

        lock = new ReentrantReadWriteLock();
    }

    public double getPrice1() throws InterruptedException {
        lock.readLock().lock();
        System.out.println("getPrice1 sleep start");
        Thread.sleep(100);
        System.out.println("getPrice1 sleep end");
        double value = price1;
        lock.readLock().unlock();
        return value;
    }

    public double getPrice2() throws InterruptedException {
        lock.readLock().lock();
        Thread.sleep(100);
        double value = price2;
        lock.readLock().unlock();
        return value;
    }

    public void setPrices(double price1, double price2) {
        System.out.println("setPrices wait……");
        lock.writeLock().lock();
        System.out.println("modify start");
        this.price1 = price1;
        this.price2 = price2;
        System.out.println("modify end");
        lock.writeLock().unlock();
    }
}


class Writer implements Runnable {
    private PricesInfo pricesInfo;

    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("Writer: Attempt to modify the prices.\n");
            pricesInfo.setPrices(Math.random() * 10, Math.random() * 8);
            System.out.printf("Writer: Prices have been modified.\n");
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}


class Reader implements Runnable {

    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + "--Price 1:" + pricesInfo.getPrice1());
                System.out.println(Thread.currentThread().getName() + "--Price 2:" + pricesInfo.getPrice2());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
