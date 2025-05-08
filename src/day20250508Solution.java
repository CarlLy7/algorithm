import java.util.PriorityQueue;

/**
 * @description:
 * @author: carl
 * @date: 2025.05.08
 * @Since: 1.0
 */

public class day20250508Solution {
    // 283 移动零
    public void moveZeroes(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[p] = nums[i];
                p++;
            }
        }
        for (int i = p; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    // 287 寻找重复数
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int left = 1, right = n - 1;
        int res = 0;
        // 具有单调性，在nums[i]<重复数的范围内，出现的小于等于nums[i]元素的个数<=i.在nums[i]>重复数的范围内，出现的nums中小于等于nums[i]的元素>i
        while (left <= right) {
            int count = 0;
            int mid = (left + right) / 2;
            for (int i = 0; i < n; i++) {
                // 寻找所有小于等于mid的元素个数
                if (nums[i] <= mid) {
                    count++;
                }
            }
            // 如果小于等于mid的元素的个数，小于等于mid，那么重复的数出现的范围在mid+1之后
            if (count <= mid) {
                left = mid + 1;
            } else {
                // 如果小于等于mid的元素的个数大于mid,那么重复的数出现的范围在mid之前
                right = mid - 1;
                res = mid;
            }
        }
        return res;
    }

    // 295 数据流的中位数
    class MedianFinder {
        // 使用两个优先级队列，一个是大顶堆，一个是小顶堆

        // 上面的梯形，放的是大数，本身是个小顶堆
        PriorityQueue<Integer> large;
        // 下面的倒三角，放的是小数，本身是一个大顶堆
        PriorityQueue<Integer> small;

        public MedianFinder() {
            large = new PriorityQueue<>();
            small = new PriorityQueue<>((a, b) -> {
                return b - a;
            });
        }

        public void addNum(int num) {
            // 两个优先级队列都会同时放进去数，一个放进去另一个会把自己排出去的数给另一个
            if (large.size() > small.size()) {
                large.offer(num);
                small.offer(large.poll());
            } else {
                small.offer(num);
                large.offer(small.poll());
            }
        }

        /**
         * 如果两边数量不一样，多出来的那个就是中位数，如果一样的话，中位数是两个中间值的平均值
         * 
         * @return
         */
        public double findMedian() {
            if (large.size() > small.size()) {
                return large.peek();
            } else if (small.size() > large.size()) {
                return small.peek();
            } else {
                return (large.peek() + small.peek()) / 2.0;
            }
        }
    }
}
