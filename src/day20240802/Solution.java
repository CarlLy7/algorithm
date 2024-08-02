package day20240802;

import java.util.PriorityQueue;
import java.util.Random;


/**
 * @description:
 * @author: Carl
 * @createDate: 2024-08-02 15:46
 * @version: 1.0
 */
public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p == root || q == root) {
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
        if (leftNode == null && rightNode == null) {
            return null;
        }
        if (leftNode != null && rightNode != null) {
            return root;
        }
        return leftNode == null ? rightNode : leftNode;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

//    int[] temp;
//
//    public int[] sortArray(int[] nums) {
//        int n = nums.length;
//        temp = new int[n];
//        sort(nums, 0, n - 1);
//        return nums;
//    }
//
//    private void sort(int[] nums, int lo, int hi) {
//        if (lo == hi) {
//            return;
//        }
//        int mid = (lo + hi) / 2;
//        //左半部分进行排序
//        sort(nums, lo, mid);
//        //右半部分进行排序
//        sort(nums, mid + 1, hi);
//        //合并整个数组
//        merge(nums, lo, mid, hi);
//    }
//
//    private void merge(int[] nums, int lo, int mid, int hi) {
//        for (int i = lo; i <= hi; i++) {
//            temp[i] = nums[i];
//        }
//        int i = lo, j = mid + 1;
//        for (int p = lo; p <= hi; p++) {
//            if (i == mid + 1) {
//                //左边合并完了
//                nums[p] = temp[j++];
//            } else if (j == hi + 1) {
//                //右半部分合并完了
//                nums[p] = temp[i++];
//            } else if (temp[i] > temp[j]) {
//                nums[p] = temp[j++];
//            } else {
//                nums[p] = temp[i++];
//            }
//        }
//    }

    //    class Pair {
//        int val, id;
//
//        public Pair(int val, int id) {
//            this.val = val;
//            this.id = id;
//        }
//    }
//
//    Pair[] temp;
//    int[] count;
//
//    public List<Integer> countSmaller(int[] nums) {
//        int n = nums.length;
//        temp = new Pair[n];
//        count = new int[n];
//        Pair[] arr = new Pair[n];
//        for (int i = 0; i < n; i++) {
//            arr[i] = new Pair(nums[i], i);
//        }
//        sort(arr, 0, n - 1);
//        List<Integer> res = new ArrayList<>();
//        for (int i : count) {
//            res.add(i);
//        }
//        return res;
//    }
//
//    private void sort(Pair[] arr, int lo, int hi) {
//        if (lo == hi) {
//            return;
//        }
//        int mid = (lo + hi) / 2;
//        sort(arr, lo, mid);
//        sort(arr, mid + 1, hi);
//        merge(arr, lo, mid, hi);
//    }
//
//    private void merge(Pair[] arr, int lo, int mid, int hi) {
//        for (int i = lo; i <= hi; i++) {
//            temp[i] = arr[i];
//        }
//        int i = lo, j = mid + 1;
//        for (int p = lo; p <= hi; p++) {
//            if (i == mid + 1) {
//                arr[p] = temp[j++];
//            } else if (j == hi + 1) {
//                arr[p] = temp[i++];
//                count[arr[p].id] += j - mid - 1;
//            } else if (temp[i].val > temp[j].val) {
//                arr[p] = temp[j++];
//            } else {
//                arr[p] = temp[i++];
//                count[arr[p].id] += j - mid - 1;
//            }
//        }
//    }
//    int[] temp;
//    int count;
//
//    public int reversePairs(int[] nums) {
//        int n = nums.length;
//        temp = new int[n];
//        sort(nums, 0, n - 1);
//        return count;
//    }
//
//    private void sort(int[] nums, int lo, int hi) {
//        if (lo == hi) {
//            return;
//        }
//        int mid = (lo + hi) / 2;
//        sort(nums, lo, mid);
//        sort(nums, mid + 1, hi);
//        merge(nums, lo, mid, hi);
//    }
//
//    private void merge(int[] nums, int lo, int mid, int hi) {
//        for (int i = lo; i <= hi; i++) {
//            temp[i] = nums[i];
//        }
//        int end = mid + 1;
//        for (int i = lo; i <= mid; i++) {
//            while (end <= hi && (long) nums[i] > (long) nums[end] * 2) {
//                end++;
//            }
//            count += end - (mid + 1);
//        }
//        int i = lo, j = mid + 1;
//        for (int p = lo; p <= hi; p++) {
//            if (i == mid + 1) {
//                nums[p] = temp[j++];
//            } else if (j == hi + 1) {
//                nums[p] = temp[i++];
//            } else if (temp[i] > temp[j]) {
//                nums[p] = temp[j++];
//            } else {
//                nums[p] = temp[i++];
//            }
//        }
//    }

    public void quickSort(int[] nums) {
        shuffle(nums);
        int n = nums.length;
        sort(nums, 0, n - 1);
    }

    private void shuffle(int[] nums) {
        Random random = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int index = i + random.nextInt(n - i);
            swap(nums, i, index);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void sort(int[] nums, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int point = partation(nums, lo, hi);
        sort(nums, lo, point - 1);
        sort(nums, point + 1, hi);
    }


    //[3,2,1,5,6,4] k=2  1 2 3 4 5 6  6-2=4
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        int n = nums.length;
        int lo = 0, hi = n - 1;
        k = n - k;
        while (lo <= hi) {
            int point = partation(nums, lo, hi);
            if (point < k) {
                lo = point + 1;
            } else if (point > k) {
                hi = point - 1;
            } else {
                return nums[point];
            }
        }
        return -1;
    }

    private int partation(int[] nums, int lo, int hi) {
        int p = nums[lo];
        int i = lo + 1, j = hi;
        while (i <= j) {
            while (i < hi && nums[i] <= p) {
                i++;
            }
            while (j > lo && nums[j] > p) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, lo, j);
        return j;
    }
}
