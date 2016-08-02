package com.kuta.demo.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @notes:用lock+condition实现生产者消费者
 * @author: kuta.li
 * @date: 2016年7月27日-下午5:03:15
 * 在Condition中，用await()替换wait()，用signal()替换 notify()，用signalAll()替换notifyAll()，
 * 对于我们以前使用传统的Object方法，Condition都能够给予实现。
 * 用lock代替synchronised  用condition代替lock
 */
public class PCDemo {

    private int depotSize; // 仓库大小
    private Lock lock; // 独占锁
    private int capacity; // 仓库容量

    private Condition fullCondition;
    private Condition emptyCondition;
    
    public PCDemo() {
        this.depotSize = 0;
        this.lock = new ReentrantLock();
        this.capacity = 15;
        this.fullCondition = lock.newCondition();
        this.emptyCondition = lock.newCondition();
    }

    public static void main(String[] args) {
        PCDemo depot = new PCDemo();
        Producer producer = new Producer(depot);
        Constumer constumer = new Constumer(depot);
        
        producer.produce(10);  
        constumer.consume(5);  
        producer.produce(15);  
        constumer.consume(10);  
        constumer.consume(15);  
        producer.produce(10);  
    }

    /**
     * @notes:入库
     * @author: kuta.li
     * @date: 2016年7月27日-下午5:08:55
     */
    public void put(int value) {
        try {
            lock.lock();
            int left = value;
            while (left > 0) {
                while (depotSize >= capacity) {
                    fullCondition.await();
                }
                // 计算可以加入仓库的数量
                int increase = depotSize + left > capacity ? capacity - depotSize : left;
                depotSize += increase;
                left -= increase;
                System.out.println(Thread.currentThread().getName() + "----要入库数量: " + value +";;实际入库数量：" + increase + ";;仓库货物数量：" + depotSize + ";;没有入库数量：" + left);
                // 通知消费者可以消费了
                emptyCondition.signal();
            }
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    /**
     * @notes:出库
     * @author: kuta.li
     * @date: 2016年7月27日-下午5:09:13
     */
    public void get(int value) {
        try {
            lock.lock();
            int left = value;
            while (left > 0) {
                while (depotSize <= 0) {
                    emptyCondition.await();
                }
                // 计算可以从仓库取出的数量
                int decrease = depotSize - left >= 0 ? left : depotSize;
                depotSize -= decrease;
                left -= decrease;
                System.out.println(Thread.currentThread().getName() + "----要消费的数量：" + value +";;实际消费的数量: " + decrease + ";;仓库现存数量：" + depotSize + ";;有多少件商品没有消费：" + left);
                // 通知生产者可以继续生产了
                fullCondition.signal();
            }
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

}


class Producer {
    private PCDemo depot;

    public Producer(PCDemo depot) {
        this.depot = depot;
    }

    public void produce(final int value) {
        new Thread() {
            public void run() {
                depot.put(value);
            }
        }.start();
    }
}


class Constumer {
    private PCDemo depot;

    public Constumer(PCDemo depot) {
        this.depot = depot;
    }

    public void consume(final int value) {
        new Thread() {
            public void run() {
                depot.get(value);
            }
        }.start();
    }
}
