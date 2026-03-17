package date202603;

import java.util.LinkedList;

/**
 * @description:
 * @author: carl
 * @date: 2026.03.17
 * @Since: 1.0
 */

public class day20260317Solution {
    // [1670] 设计前中后队列
    private class FrontMiddleBackQueue {
        // 左边链表
        private LinkedList<Integer> left;
        // 右边链表，并且维护左少右大
        private LinkedList<Integer> right;

        public FrontMiddleBackQueue() {
            left = new LinkedList<>();
            right = new LinkedList<>();
        }

        /**
         * 平衡左右两边的长度，始终保持，右边最多比左边大1个元素
         */
        public void balance() {
            if (right.size() > left.size() + 1) {
                left.addLast(right.removeFirst());
            }
            if (left.size() > right.size()) {
                right.addFirst(left.removeLast());
            }
        }

        /**
         * 将 val 添加到队列的 最前面
         * 
         * @param val
         */
        public void pushFront(int val) {
            left.addFirst(val);
            balance();
        }

        /**
         * 将 val 添加到队列的 正中间
         * 
         * @param val
         */
        public void pushMiddle(int val) {
            // 如果是偶数则需要放到右边列表，因为右边长
            if ((left.size() + right.size()) % 2 == 0) {
                right.addFirst(val);
            } else {
                // 奇数放在左边
                left.addLast(val);
            }
            balance();
        }

        /**
         * 将 val 添加到队里的 最后面
         * 
         * @param val
         */
        public void pushBack(int val) {
            right.addLast(val);
            balance();
        }

        /**
         * 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1
         * 
         * @return
         */
        public int popFront() {
            if (left.size() + right.size() == 0) {
                return -1;
            }
            // 如果只有一个元素，肯定是在右边，因为右边长，所以需要从右边出
            if (left.size() + right.size() == 1) {
                return right.removeFirst();
            }
            Integer res = left.removeFirst();
            balance();
            return res;
        }

        /**
         * 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1
         * 
         * @return
         */
        public int popMiddle() {
            if (left.size() + right.size() == 0) {
                return -1;
            }
            // 如果只有一个元素，肯定是在右边，因为右边长，所以需要从右边出
            if (left.size() + right.size() == 1) {
                return right.removeFirst();
            }
            // 如果是偶数则从左边删
            if ((left.size() + right.size()) % 2 == 0) {
                Integer i = left.removeLast();
                balance();
                return i;
            } else {
                // 如果是奇数从右边删
                Integer i = right.removeFirst();
                balance();
                return i;
            }
        }

        /**
         * 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1
         * 
         * @return
         */
        public int popBack() {
            if (left.size() + right.size() == 0) {
                return -1;
            }
            // 如果只有一个元素，肯定是在右边，因为右边长，所以需要从右边出
            if (left.size() + right.size() == 1) {
                return right.removeFirst();
            }
            Integer i = right.removeLast();
            balance();
            return i;
        }
    }
}
