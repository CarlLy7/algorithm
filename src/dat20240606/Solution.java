package dat20240606;

import java.util.Random;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-06 11:26
 * @version: 1.0
 */
public class Solution {
    //101   0111  100  111111111100100010->011111111100100011
    public long minimumSteps(String s) {
        long res = 0;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                sum++;
            } else {
                res += sum;
            }
        }
        return res;
    }

    //    private int[] nums;
//    private Random random = new Random();
//
//    public Solution(int[] nums) {
//        this.nums = nums;
//    }
//
//    public int[] reset() {
//        return nums;
//    }
//
//    public int[] shuffle() {
//        int n = nums.length;
//        int[] arr = Arrays.copyOf(nums, n);
//        for (int i = 0; i < n; i++) {
//            // [i,n-1]
//            int rand = random.nextInt(n - i) + i;
//            swap(arr, i, rand);
//        }
//        return arr;
//    }
//
//    private void swap(int[] arr, int i, int j) {
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
//    }
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    ListNode head;
    Random random = new Random();

    public Solution(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        int i = 0, res = 0;
        ListNode p = head;
        while (p != null) {
            i++;
            if (0 == random.nextInt(i)) {
                res = p.val;
            }
            p = p.next;
        }
        return res;
    }

    public int[] getRandom(int k) {
        int[] res = new int[k];

        ListNode p = head;
        // 前k个元素先加到结果中
        for (int j = 0; j < k && p != null; j++) {
            res[j] = p.val;
            p = p.next;
        }
        int i = k;
        while (p != null) {
            i++;
            //[0,i-1]
            int rand = random.nextInt(i);
            // k/i 的概率被选择
            if (rand < k) {
                res[rand] = p.val;
            }
            p = p.next;
        }
        return res;
    }
//    private int[] nums;
//    Random random;
//
//    public Solution(int[] nums) {
//        this.nums = nums;
//        this.random = new Random();
//    }
//
//    public int pick(int target) {
//        int count = 0, res = -1;
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != target) {
//                continue;
//            }
//            count++;
//            if (0 == random.nextInt(count)) {
//                res = i;
//            }
//        }
//        return res;
//    }
}
