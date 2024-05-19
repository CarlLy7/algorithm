package fourClass;

/**
 * @description: 使用双向链表实现一个双端队列
 * @author: lyq
 * @createDate: 30/8/2022
 * @version: 1.0
 */
public class DoubleLinkedListToQueue {
    /**
     * 定义一个双向链表结构
     */
    public static class DoubleNode<V> {
        private DoubleNode<V> last;
        private DoubleNode<V> next;
        private V value;

        public DoubleNode(V value) {
            this.value = value;
        }

        public DoubleNode() {
            last = null;
            next = null;
        }
    }

    /**
     * 定义双端队列结构
     */
    public static class MyDeque<V> {
        private DoubleNode<V> head;
        private DoubleNode<V> tail;
        private int size;

        public MyDeque() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        /**
         * 从头插
         *
         * @param value
         */
        public void pushHead(V value) {
            DoubleNode<V> cur = new DoubleNode<>(value);
            if (head == null) {
                head = cur;
                tail=cur;
            } else {
               cur.next=head;
               head.last=cur;
               head=cur;
            }
            size++;
        }

        /**
         * 从尾插
         *
         * @param value
         */
        public void pushTail(V value) {
            DoubleNode<V> cur = new DoubleNode<>(value);
            if (tail == null) {
                tail = cur;
                head=cur;
            } else {
               cur.last=tail;
               tail.next=cur;
               tail=cur;
            }
            size++;
        }

        /**
         * 从头出队
         */
        public V popHead() {
            V ans = null;
            if (head != null) {
                ans = head.value;
                if(head==tail){ //队列中只有一个元素
                    head=null;
                    tail=null;
                }else{
                    head = head.next;
                    head.last = null;
                }
                size--;
            }
            return ans;
        }

        /**
         * 从尾出队
         */
        public V popTail() {
            V ans = null;
            if (tail != null) {
                ans = tail.value;
                if(tail==head){
                    head=null;
                    tail=null;
                }else{
                    tail = tail.last;
                    tail.next=null;
                }
               size--;
            }
            return ans;
        }

        /**
         * 从头peek
         * @return
         */
        public V peekHead(){
            V ans=null;
            if(head!=null){
                ans=head.value;
            }
            return ans;
        }
        /**
         * 从尾peek
         * @return
         */
        public V peekTail(){
            V ans=null;
            if(tail!=null){
                ans=tail.value;
            }
            return ans;
        }

    }
}
