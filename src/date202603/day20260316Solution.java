package date202603;

import java.util.ArrayDeque;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.16
 * @Since: 1.0
 */

public class day20260316Solution {
    // [622] 设计循环队列

    private class ArrayQueue<E> {
        // 当前数组中的元素个数
        private int size;
        private E[] data;
        private int first;
        private int last;
        private final static int INIT_CAPACITY = 2;

        public ArrayQueue() {
            this(INIT_CAPACITY);
        }

        public ArrayQueue(int initCap) {
            this.size = 0;
            this.data = (E[])new Object[initCap];
            this.first = this.last = 0;
        }

        /**
         * 更新数组大小
         * 
         * @param newCap
         */
        public void resize(int newCap) {
            E[] newData = (E[])new Object[newCap];
            for (int i = 0; i < size; i++) {
                newData[i] = data[(first + i) % data.length];
            }
            first = 0;
            last = size;
            data = newData;
        }

        /**
         * 队尾增加元素
         * 
         * @param value
         * @return
         */
        public void enQueue(E value) {
            data[last] = value;
            last++;
            // 如果此时已经满了，则last要回到头部
            if (last == data.length) {
                last = 0;
            }
            size++;
        }

        /**
         * 从队头获取一个元素
         * 
         * @return
         */
        public E deQueue() {
            if (isEmpty()) {
                throw new RuntimeException();
            }
            E oldValue = data[first];
            data[first] = null;
            first++;
            if (first == data.length) {
                first = 0;
            }
            size--;
            return oldValue;
        }

        /**
         * 取出队头元素
         * 
         * @return
         */
        public E front() {
            if (isEmpty()) {
                throw new RuntimeException();
            }
            return data[first];
        }

        /**
         * 取出队尾元素
         * 
         * @return
         */
        public E rear() {
            if (isEmpty()) {
                throw new RuntimeException();
            }
            // 因为是左闭右开区间，last指向的是下一个存放位置
            if (last == 0) {
                return data[data.length - 1];
            }
            return data[last - 1];
        }

        /**
         * 队列是否满
         * 
         * @return
         */
        public boolean isFull() {
            return data.length == size;
        }

        /**
         * 队列中元素是否是空
         * 
         * @return
         */
        public boolean isEmpty() {
            return size == 0;
        }

    }

    private class MyCircularQueue {
        private ArrayQueue<Integer> queue;

        public MyCircularQueue(int k) {
            queue = new ArrayQueue<>(k);
        }

        public boolean enQueue(int value) {
            if (queue.isFull()) {
                return false;
            }
            queue.enQueue(value);
            return true;
        }

        public boolean deQueue() {
            if (queue.isEmpty()) {
                return false;
            }
            queue.deQueue();
            return true;
        }

        public int Front() {
            if (queue.isEmpty()) {
                return -1;
            }
            return queue.front();
        }

        public int Rear() {
            if (queue.isEmpty()) {
                return -1;
            }
            return queue.rear();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public boolean isFull() {
            return queue.isFull();
        }
    }

    // [641] 设计循环双端队列
    private class MyCircularDeque {
        private MyArrayDequeue<Integer> queue;
        private int maxCap;

        public MyCircularDeque(int k) {
            queue = new MyArrayDequeue<>(k);
            this.maxCap = k;
        }

        public boolean insertFront(int value) {
            if (queue.isFull()) {
                return false;
            }
            queue.insertFront(value);
            return true;
        }

        public boolean insertLast(int value) {
            if (queue.isFull()) {
                return false;
            }
            queue.insertLast(value);
            return true;
        }

        public boolean deleteFront() {
            if (queue.isEmpty()) {
                return false;
            }
            queue.deleteFront();
            return true;
        }

        public boolean deleteLast() {
            if (queue.isEmpty()) {
                return false;
            }
            queue.deleteLast();
            return true;
        }

        public int getFront() {
            if (queue.isEmpty()) {
                return -1;
            }
            return queue.getFront();
        }

        public int getRear() {
            if (queue.isEmpty()) {
                return -1;
            }
            return queue.getRear();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

        public boolean isFull() {
            return queue.isFull();
        }
    }

    private class MyArrayDequeue<E> {
        // 当前数组中有多少个元素
        public int size;
        // 队头指针
        public int first;
        // 队尾指针,左闭右开区间
        public int last;

        public E[] data;

        public static final int INIT_CAPACITY = 2;

        public MyArrayDequeue(int capacity) {
            this.data = (E[])new Object[capacity];
            this.size = 0;
            this.first = this.last = 0;
        }

        public MyArrayDequeue() {
            this(INIT_CAPACITY);
        }

        /**
         * 重置容量大小
         * 
         * @param newCapacity
         */
        public void resize(int newCapacity) {
            E[] newData = (E[])new Object[newCapacity];
            // 将老数组中的元素移过去
            for (int i = 0; i < size; i++) {
                newData[i] = data[(first + i) % data.length];
            }
            first = 0;
            last = size;
            data = newData;
        }

        /**
         * 队头插入元素
         * 
         * @param value
         */
        public void insertFront(E value) {
            // 如果此时队头指针在头部，则新增的应该放在尾部
            if (first == 0) {
                first = data.length - 1;
            } else {
                first--;
            }
            data[first] = value;
            size++;
        }

        /**
         * 队尾插入元素
         * 
         * @param value
         * @return
         */
        public void insertLast(E value) {

            data[last] = value;
            last++;
            if (last == data.length) {
                last = 0;
            }
            size++;
        }

        /**
         * 删除队头元素
         * 
         * @return
         */
        public E deleteFront() {
            if (isEmpty()) {
                throw new RuntimeException();
            }

            E oldValue = data[first];
            data[first] = null;
            first++;
            if (first == data.length) {
                first = 0;
            }
            size--;
            return oldValue;
        }

        /**
         * 删除队尾元素
         * 
         * @return
         */
        public E deleteLast() {
            if (isEmpty()) {
                throw new RuntimeException();
            }

            if (last == 0) {
                last = data.length - 1;
            } else {
                last--;
            }
            E oldValue = data[last];
            data[last] = null;
            size--;
            return oldValue;
        }

        public E getFront() {
            if (isEmpty()) {
                throw new RuntimeException();
            }
            return data[first];
        }

        public E getRear() {
            if (isEmpty()) {
                throw new RuntimeException();
            }
            if (last == 0) {
                return data[data.length - 1];
            }
            return data[last - 1];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == data.length;
        }

    }
}
