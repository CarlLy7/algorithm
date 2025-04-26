import java.util.Stack;

/**
 * @description:
 * @author: carl
 * @createDate: 2025-04-26 13:26
 * @Since: 1.0
 */
public class day20250426Solution {
    // 153 寻找旋转排序数组中的最小值
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return nums[left];
    }

    // 155 最小栈
    private class MinStack {
        //记录所有的栈中元素
        Stack<Integer> stack;
        //最小值元素栈
        Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int val) {
            //如果最小值栈中为空，或者当前值比最小栈中最小值还要小，往最小值栈中放入当前值
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
            stack.push(val);
        }

        public void pop() {
            //如果我从原始栈中删除的元素恰好等于最小栈中的最小值，那么这个最小值也应该同事从最小栈中删除
            if (stack.peek().equals(minStack.peek())) {
                minStack.pop();
            }
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    // 160 相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }
        return p1;
    }

    //189 轮转数组
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        //全部翻转
        traverse(nums, 0, n - 1);
        //前k个数翻转
        traverse(nums, 0, k - 1);
        //后n-k个数翻转
        traverse(nums, k, n - 1);
    }

    /**
     * 翻转nums中[start..end]的数
     *
     * @param nums
     * @param start
     * @param end
     */
    private void traverse(int[] nums, int start, int end) {
        int left = start, right = end;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
