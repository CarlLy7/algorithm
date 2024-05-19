package fourClass;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description: 使用链表实现队列和栈
 * @author: lyq
 * @createDate: 29/8/2022
 * @version: 1.0
 */
public class linkedListToQueueAndStack {
    /**
     * 定义单链表结构
     *
     * @param <V>
     */
    public static class Node<V> {
        private V value;
        private Node<V> next;

        public Node(V value) {
            this.value = value;
        }

        public Node() {
        }
    }

    /**
     * 使用单链表来定义队列结构
     *
     * @param <V>
     */
    public static class MyQueue<V> {
        private Node<V> head;//头指针
        private Node<V> tail;//尾指针
        private int size; //节点数量

        //初始化
        public MyQueue() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        /**
         * 入队的方法
         */
        public void offer(V value) {
            Node<V> node = new Node<>(value);
            if (tail == null) { //如果尾结点为空，说明队列为空，加到第一个上
                head = node;
                tail = node;
            } else {
                //如果tail不为空，说明此时队列中有元素
                tail.next = node;
                tail = node; //尾指针也要动
            }
            size++;
        }

        /**
         * 出队的方法
         */
        public V pop() {
            V ans = null;
            if (head != null) { //如果此时队列不为空
                ans = head.value;
                head = head.next;
                size--;
            }
            if (head == null) { //此时队列为空
                tail = null; //如果tail不等于null的话可能有一个脏数据不能释放
            }
            return ans;
        }

        /**
         * 得到队首元素，但是不删除
         */
        public V peek() {
            V ans = null;
            if (head != null) {
                ans = head.value;
            }
            return ans;
        }
    }

    /**
     * 写一个对数器来判断自定义的队列对不对
     */
    public static void testQueue() {
        MyQueue<Integer> myQueue = new MyQueue<>();
        Queue<Integer> test = new LinkedList<>();
        int testTimes = 5000000;
        int maxValue = 1000000;
        /**
         * 小于33%的概率写入，大于等于33%小于66%的概率取出数据，大于等于66%小于等于1的概率查看队首元素
         */
        System.out.println("测试开始!");
        for (int i = 0; i < testTimes; i++) {
            if (myQueue.isEmpty() != test.isEmpty()) {
                System.out.println("测试错误");
                break;
            }
            if (myQueue.size() != test.size()) {
                System.out.println("测试错误");
                break;
            }
            double probability = Math.random();
            if (probability < 0.33) {
                int value = (int) (Math.random() * maxValue);
                myQueue.offer(value);
                test.offer(value);

            } else if (probability < 0.66) {
                if (!myQueue.isEmpty()) {
                    int a1 = myQueue.pop();
                    int a2 = test.poll();
                    if (a1 != a2) {
                        System.out.println("测试错误");
                        break;
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    int p1 = myQueue.peek();
                    int p2 = test.peek();
                    if (p1 != p2) {
                        System.out.println("测试错误");
                        break;
                    }
                }
            }
        }
        if (myQueue.size() != test.size()) {
            System.out.println("测试错误");
        }
        //是要是myQueue中不为空就取出来和test中的对比
        while (!myQueue.isEmpty()) {
            int a1 = myQueue.pop();
            int a2 = test.poll();
            if (a1 != a2) {
                System.out.println("测试错误");
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 使用链表定义一个栈
     *
     * @param <V>
     */
    public static class MyStack<V> {
        private Node<V> head;
        private int size;

        public MyStack() {
            head = null;
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void push(V value) {
            Node<V> cur = new Node<>(value);
            if (head == null) { //如果栈中没有数据
                head = cur;
            }else{ //如果栈中有数据
                cur.next=head;
                head=cur;
            }
            size++;
        }
        public V pop(){
            V ans=null;
            if(head!=null){
                ans=head.value;
                head=head.next;
                size--;
            }
            return ans;
        }

        public V peek(){
            V ans=null;
            if(head!=null){
                ans= head.value;
            }
            return ans;
        }
    }
    /**
     * 写一个对数器来判断自定义的栈对不对
     */
    public static void testStack() {
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> test=new Stack<>();
        int testTimes = 5000000;
        int maxValue = 1000000;
        /**
         * 小于33%的概率写入，大于等于33%小于66%的概率取出数据，大于等于66%小于等于1的概率查看队首元素
         */
        System.out.println("测试开始!");
        for (int i = 0; i < testTimes; i++) {
            if (myStack.isEmpty() != test.isEmpty()) {
                System.out.println("测试错误");
                break;
            }
            if (myStack.size() != test.size()) {
                System.out.println("测试错误");
                break;
            }
            double probability = Math.random();
            if (probability < 0.33) {
                int value = (int) (Math.random() * maxValue);
                myStack.push(value);
                test.push(value);

            } else if (probability < 0.66) {
                if (!myStack.isEmpty()) {
                    int a1 = myStack.pop();
                    int a2 = test.pop();
                    if (a1 != a2) {
                        System.out.println("测试错误");
                        break;
                    }
                }
            } else {
                if (!myStack.isEmpty()) {
                    int p1 = myStack.peek();
                    int p2 = test.peek();
                    if (p1 != p2) {
                        System.out.println("测试错误");
                        break;
                    }
                }
            }
        }
        if (myStack.size() != test.size()) {
            System.out.println("测试错误");
        }
        //是要是myQueue中不为空就取出来和test中的对比
        while (!myStack.isEmpty()) {
            int a1 = myStack.pop();
            int a2 = test.pop();
            if (a1 != a2) {
                System.out.println("测试错误");
                break;
            }
        }
        System.out.println("测试结束");
    }

    public static void main(String[] args) {
        testQueue();
        testStack();
    }
}
