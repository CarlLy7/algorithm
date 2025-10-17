package hot100;

import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.16
 * @Since: 1.0
 */

public class day20251016Solution {
    // [895] 最大频率栈
    class FreqStack {
        private HashMap<Integer, Integer> keyToFre;
        private HashMap<Integer, Stack<Integer>> freToKeys;
        private int maxFre;

        public FreqStack() {
            keyToFre = new HashMap<>();
            freToKeys = new HashMap<>();
            this.maxFre = 0;
        }

        public void push(int val) {
            int fre = keyToFre.getOrDefault(val, 0) + 1;
            keyToFre.put(val, fre);
            freToKeys.putIfAbsent(fre, new Stack<>());
            freToKeys.get(fre).add(val);
            this.maxFre = Math.max(fre, this.maxFre);
        }

        public int pop() {
            Stack<Integer> keyList = freToKeys.get(this.maxFre);
            // 只是从fre->keyList中删除了，但是这个元素不删除
            Integer key = keyList.pop();
            // 更新这个key的fre
            int fre = keyToFre.get(key) - 1;
            keyToFre.put(key, fre);
            if (keyList.isEmpty()) {
                this.maxFre--;
            }
            return key;
        }
    }

    // [380] O(1) 时间插入、删除和获取随机元素

    class RandomizedSet {
        private List<Integer> nums;
        private HashMap<Integer, Integer> valToIndex;
        private Random random;

        public RandomizedSet() {
            nums = new ArrayList<>();
            valToIndex = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (valToIndex.containsKey(val)) {
                return false;
            }
            int index = nums.size();
            valToIndex.put(val, index);
            nums.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!valToIndex.containsKey(val)) {
                return false;
            }
            Integer index = valToIndex.get(val);
            int lastVal = nums.get(nums.size() - 1);
            valToIndex.put(lastVal, index);
            nums.set(index, lastVal);
            nums.remove(nums.size() - 1);
            valToIndex.remove(val);
            return true;
        }

        public int getRandom() {
            return nums.get(random.nextInt(nums.size()));
        }
    }

    // [710] 黑名单中的随机数
    class Solution {
        // 黑名单值和索引的映射
        private HashMap<Integer, Integer> blackMapping;
        private int size;
        private Random random;

        public Solution(int n, int[] blacklist) {
            this.size = n - blacklist.length;
            random = new Random();
            blackMapping = new HashMap<>();
            for (int b : blacklist) {
                blackMapping.put(b, 666);
            }
            int last = n - 1;
            for (int black : blacklist) {
                // 如果本身就在黑名单映射索引范围内，不操作
                if (black >= size) {
                    continue;
                }
                // 如果此时要映射过去的索引，本身就是一个黑名单，跳过
                while (blackMapping.containsKey(last)) {
                    last--;
                }
                blackMapping.put(black, last);
                last--;
            }
        }

        public int pick() {
            int rand = random.nextInt(size);
            // 如果随机的这个数字，正好是黑名单，则使用新的映射
            if (blackMapping.containsKey(rand)) {
                return blackMapping.get(rand);
            }
            return rand;
        }
    }
}
