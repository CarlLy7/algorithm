import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.03
 * @Since: 1.0
 */

public class day20250703Solution {
    // [2073] 买票需要的时间
    public int timeRequiredToBuy(int[] tickets, int k) {
        int res = 0;
        for (int i = 0; i < tickets.length; i++) {
            // k前面的人最多买tickets[k]张票
            if (i <= k) {
                res += Math.min(tickets[i], tickets[k]);
            } else {
                // 排在k后面的人，最多买tickets[k]-1张票
                res += Math.min(tickets[k] - 1, tickets[i]);
            }
        }
        return res;
    }

    // [290] 单词规律
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> patternToWord = new HashMap<>();
        String[] words = s.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }
        // 记录已经有对应pattern的单词
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            // 如果是第一次碰到这种匹配规则
            if (!patternToWord.containsKey(c)) {
                if (set.contains(word)) {
                    return false;
                }
                patternToWord.put(c, word);
            } else {
                // 如果不是第一次碰到这个匹配规则
                if (!patternToWord.get(c).equals(word)) {
                    return false;
                }
            }
            set.add(word);
        }
        return true;
    }

    // [387] 字符串中的第一个唯一字符
    public int firstUniqChar(String s) {
        // 使用一个数组来记录，每个字母出现的次数
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (chars[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
