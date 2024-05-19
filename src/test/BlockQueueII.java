package test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @description: 手写一个阻塞队列
 * @author: lyq
 * @createDate: 25/3/2023
 * @version: 1.0
 */
public class BlockQueueII {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count=new CountDownLatch(2);
        BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(5);
        Thread producer=new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    queue.put(i);
                    System.out.println("生产者生产了商品："+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                   Thread.currentThread().interrupt();
                }
            }
            count.countDown();
        },"生产者线程");

        Thread consumer=new Thread(()->{
         for (int i=0;i<10;i++){
             try {
                 Integer product = queue.take();
                 System.out.println("消费了商品:"+product);
             } catch (InterruptedException e) {
                 e.printStackTrace();
                 Thread.currentThread().interrupt();
             }
         }
         count.countDown();
        },"消费者线程");

        producer.start();
        consumer.start();
        count.await();
        System.exit(0);
    }



}
