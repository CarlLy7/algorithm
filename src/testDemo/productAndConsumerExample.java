package testDemo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 手写一个生产者消费者模式
 * @author: lyq
 * @createDate: 14/3/2023
 * @version: 1.0
 */
public class productAndConsumerExample {
    public static void main(String[] args) {
        Queue<Integer> sharedQueue=new LinkedList<>(); //共享的队列，定义了最多可以生产多少商品
        Thread productThread=new Thread(new product(sharedQueue));
        Thread consumerThread=new Thread(new consumer(sharedQueue));
        productThread.start();
        consumerThread.start();
    }

}
class product implements Runnable{
    private final Queue<Integer> sharedQueue;

    public product(Queue<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (sharedQueue){
                while(sharedQueue.size()>=5){
                    try {
                        System.out.println("队列中已经满了，不能进行生产了");
                        sharedQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("生产一个商品到队列中");
                sharedQueue.add(i);
                sharedQueue.notifyAll();//生产了那就去唤醒消费者
            }
        }
    }
}

class consumer implements Runnable{
    private final Queue<Integer> sharedQueue;

    public consumer(Queue<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (sharedQueue){
                while(sharedQueue.isEmpty()){
                    try {
                        System.out.println("没有产品，无法进行消费");
                        sharedQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("有产品可以进行消费了");
                sharedQueue.poll();
                //消费之后唤醒其他的线程
                sharedQueue.notifyAll();
            }
        }
    }
}
