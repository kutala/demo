package com.kuta.demo.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @notes:使用 BlockingQueue实现生产者消费者
 * @author: kuta.li
 * @date: 2016年7月28日-上午11:10:18
 * BlockingQueue初始化的时候设置大小、使用put(e)如果容量满了则会阻塞、使用take()如果容量为0则会阻塞
 */
public class PCDemo {
    
    public static void main(String[] args) throws InterruptedException {
        //测试生产者消费者
        testProducerConsumer();
    }

    private static void testProducerConsumer() {
        //默认大小是Integer.MAX_VALUE
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(2);
        
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        
        for (int i = 0; i < 5; i++) {
            new Thread(producer, "生产线程" + i).start();
            new Thread(consumer, "消费线程" + i).start();
        }
    }

}

class Producer implements Runnable{
    
    BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String temp = "A producer,生产线程：" + Thread.currentThread().getName();
            System.out.println("I hava made a product：" + Thread.currentThread().getName());
            queue.put(temp);//队列满了会阻塞当前线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {

    BlockingQueue<String> queue;
    
    public Consumer(BlockingQueue<String> queue){
        this.queue = queue;
    }
    
    @Override
    public void run() {
        String temp;
        try {
            temp = queue.take();//当队列为空的时候会阻塞线程
            System.out.println(temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
