package com.kuta.demo.concurrent.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @notes: 在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待
 * @author: kuta.li
 * @date: 2016年7月29日-下午5:53:06
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        // 启动会议室线程，等待与会人员参加会议
        Conference conference = new Conference(3);
        new Thread(conference).start();

        for (int i = 0; i < 3; i++) {
            Participater participater = new Participater("chenssy-0" + i, conference);
            Thread thread = new Thread(participater);
            thread.start();
        }
    }

}


/**
 * @notes:会议
 * @author: kuta.li
 * @date: 2016年7月29日-下午5:52:28
 */
class Conference implements Runnable {
    private final CountDownLatch countDown;

    public Conference(int count) {
        countDown = new CountDownLatch(count);
    }

    /**
     * 与会人员到达，调用arrive方法，到达一个CountDownLatch调用countDown方法，锁计数器-1
     * 
     * @param name
     */
    public void arrive(String name) {
        System.out.println(name + "到达.....");
        // 调用countDown()锁计数器 - 1
        countDown.countDown();
        System.out.println("还有 " + countDown.getCount() + "没有到达...");
    }

    @Override
    public void run() {
        System.out.println("准备开会，参加会议人员总数为：" + countDown.getCount());
        // 调用await()等待所有的与会人员到达
        try {
            countDown.await();
        } catch (InterruptedException e) {
        }
        System.out.println("所有人员已经到达，会议开始.....");
    }
}


/**
 * @notes:参会人
 * @author: kuta.li
 * @date: 2016年7月29日-下午5:52:19
 */
class Participater implements Runnable {
    private String name;
    private Conference conference;

    public Participater(String name, Conference conference) {
        this.name = name;
        this.conference = conference;
    }

    @Override
    public void run() {
        conference.arrive(name);
    }
}
