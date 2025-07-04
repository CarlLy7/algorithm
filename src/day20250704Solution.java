import java.util.*;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.04
 * @Since: 1.0
 */

public class day20250704Solution {
    // [448] 找到所有数组中消失的数字
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        int[] num = new int[n + 1];
        for (int i : nums) {
            num[i]++;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (num[i] == 0) {
                res.add(i);
            }
        }
        return res;
    }

    // [389] 找不同
    public char findTheDifference(String s, String t) {
        int res = 0;
        for (char c : s.toCharArray()) {
            res = res ^ c;
        }
        for (char c : t.toCharArray()) {
            res = res ^ c;
        }
        return (char)res;
    }

    // [692] 前K个高频单词。。。。
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((entry1, entry2) -> {
            // 如果出现次数相同，则按照字典从大到小排序
            if (entry1.getValue() == entry2.getValue()) {
                return entry2.getKey().compareTo(entry1.getKey());
            }
            // 按照出现次数从小到大排序
            return entry1.getValue().compareTo(entry2.getValue());
        });
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            queue.offer(entry);
            // 如果大于k个元素了就是删除，只保留出现次数最多的k个数
            while (queue.size() > k) {
                queue.poll();
            }
        }
        LinkedList<String> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            // 因为是按照次数从小到大排序的，所以拿出来的进行头插
            res.addFirst(queue.poll().getKey());
        }
        return res;
    }
}
