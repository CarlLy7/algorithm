package Scene;

import com.oracle.jrockit.jfr.Producer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * @description:
 * @author: lyq
 * @createDate: 28/3/2023
 * @version: 1.0
 */
public class ProductAndConsumer {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        int maxSize = 10;
        //创建多个消费者线程
        Producer producer1 = new Producer(queue, maxSize, "生产者1");
        Producer producer2 = new Producer(queue, maxSize, "生产者2");
        Producer producer3 = new Producer(queue, maxSize, "生产者3");

        //消费者
        Consumer consumer = new Consumer(queue, "消费者", 5);
        producer1.start();
        producer2.start();
        producer3.start();
        consumer.start();

    }

    static class Producer extends Thread {
        private Queue<Integer> queue;
        private int maxSize;
        private String name;

        public Producer(Queue<Integer> queue, int maxSize, String name) {
            super(name);
            this.queue = queue;
            this.maxSize = maxSize;
            this.name = name;
        }

        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == maxSize) {
                        System.out.println("队列已经满了" + name);
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //随机生产一个商品
                    int product = (int) (Math.random() * 100);
                    System.out.println(name + "生产商品" + product);
                    queue.add(product);
                    queue.notifyAll();
                }
            }
        }
    }

    static class Consumer extends Thread {
        private Queue<Integer> queue;
        private String name;
        private int maxConsum;

        public Consumer(Queue<Integer> queue, String name, int maxConsum) {
            super(name);
            this.queue = queue;
            this.name = name;
            this.maxConsum = maxConsum;
        }

        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        //如果队列空的我们就等待
                        System.out.println("队列空，等待");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //消费
                    int productConsum = 0;
                    while (!queue.isEmpty() && productConsum < maxConsum) {
                        int cur = queue.remove();
                        System.out.println(name + "消费" + cur);
                        productConsum++;
                    }
                    try {
                        //控制消费速率
                        Thread.sleep(1000 / maxConsum);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.notifyAll();
                }
            }
        }
    }

}
