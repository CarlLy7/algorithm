package testDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @description: 手写一个阻塞队列
 * @author: lyq
 * @createDate: 14/3/2023
 * @version: 1.0
 */
public class blockQueueExample {
    public static void main(String[] args) {
        //注意：java中的阻塞队列中的ArrayBlockingQueue对于阻塞队列的实现中的put和take方法都是阻塞式的
        //那么是如何实现阻塞式的呢，通过看源码发现是通过可重入锁实现的，ReentrantLock,可重入锁依赖于AQS
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);//阻塞队列
        Thread product = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    blockingQueue.put(i);
                    System.out.println("生产者生产了产品" + i);
                } catch (InterruptedException e) {

                }
            }
        });
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    int res = blockingQueue.take();
                    System.out.println("消费者消费了" + res);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        product.start();
        consumer.start();

    }
}
