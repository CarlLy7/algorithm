package fourClass;

/**
 * @description: 单链表的基本算法
 * @author: lyq
 * @createDate: 28/8/2022
 * @version: 1.0
 */
public class linkNodeExcise {
    /**
     * 定义一个单链表结构
     */
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node() {
        }
    }

    /**
     * 定义一个双链表结构
     */
    public static class DoubleNode {
        private int value;
        private DoubleNode last;
        private DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }

        public DoubleNode() {
        }
    }

    /**
     * 反转单链表
     *
     * @param head
     * @return
     */
    public static Node reversalNode(Node head) {
        Node next = new Node();
        Node pre = new Node();
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 反转双链表的方法
     */
    public static DoubleNode reversalDoubleNode(DoubleNode head) {
        DoubleNode pre = new DoubleNode();
        DoubleNode next = new DoubleNode();
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

}
