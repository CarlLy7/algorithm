import java.util.Arrays;
import java.util.Iterator;

/**
 * @description:
 * @author: carl
 * @date: 2025.06.13
 * @Since: 1.0
 */

public class day20250613Solution {

    // [284] 窥视迭代器
    private class PeekingIterator implements Iterator<Integer> {
        private Iterator<Integer> iterator;
        // 提前将下一个元素值缓存起来
        private Integer nextVal;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
            nextVal = iterator.next();
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return nextVal;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            Integer res = nextVal;
            if (iterator.hasNext()) {
                nextVal = iterator.next();
            } else {
                nextVal = null;
            }
            return res;
        }

        @Override
        public boolean hasNext() {
            return nextVal != null;
        }
    }

    // [319] 灯泡开关
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }

    // [406] 根据身高重建队列
    public int[][] reconstructQueue(int[][] people) {
        // 先按照身高从大到小进行排序，如果身高相同再按照k从小到大排序
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        insertSort(people);
        return people;
    }

    private void insertSort(int[][] people) {
        int n = people.length;
        int index = 0;
        while (index < n) {
            // 从后往前遍历
            for (int i = index; i > 0; i--) {
                int k = people[i][1];
                // 前面虽然有比这个人高的，但是这个人的k小，所以需要往前移动
                if (k < i) {
                    int[] temp = people[i];
                    people[i] = people[i - 1];
                    people[i - 1] = temp;
                } else {
                    break;
                }
            }
            index++;
        }
    }
}
