import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.14
 * @Since: 1.0
 */

public class day20250714Solution {
    // [969] 煎饼排序
    LinkedList<Integer> res = new LinkedList<>();

    public List<Integer> pancakeSort(int[] arr) {
        sort(arr, arr.length);
        return res;
    }

    private void sort(int[] arr, int n) {
        if (n == 1) {
            return;
        }
        // 记录最大数以及它对应的索引
        int maxVal = 0;
        int maxValIndex = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
                maxValIndex = i;
            }
        }
        // 将最大数翻转到开始
        reverse(arr, 0, maxValIndex);
        res.add(maxValIndex + 1);

        // 将最大的数翻转到最下面
        reverse(arr, 0, n - 1);
        res.add(n);

        // 递归处理剩下的n-1元素
        sort(arr, n - 1);
    }

    private void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    // [LCR 123] 图书整理 I
    public int[] reverseBookList(ListNode head) {
        LinkedList<Integer> ans = new LinkedList<>();
        if (head == null) {
            return new int[] {};
        }
        ListNode pre = null;
        ListNode cur = head, nxt = head;
        while (cur != null) {
            ans.addFirst(cur.val);
            nxt = nxt.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }

    // [LCR 139] 训练计划 I
    public int[] trainingPlan(int[] actions) {
        int n = actions.length;
        int[] res = new int[n];
        int start = 0, end = n - 1;
        for (int i = 0; i < n; i++) {
            if (actions[i] % 2 != 0) {
                res[start] = actions[i];
                start++;
            } else {
                res[end] = actions[i];
                end--;
            }
        }
        return res;
    }

    private class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
