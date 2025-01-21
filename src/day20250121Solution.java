import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: carl
 * @date: 2025/1/21
 */

public class day20250121Solution {

    // 3
    public int lengthOfLongestSubString(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int res = Integer.MIN_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.get(d) - 1);
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    // 438 s中找t
    // public List<Integer> findAnagrams(String s, String t) {
    // List<Integer> res = new ArrayList<>();
    // Map<Character, Integer> window = new HashMap<>();
    // Map<Character, Integer> need = new HashMap<>();
    // int valid = 0;
    // for (char c : t.toCharArray()) {
    // need.put(c, need.getOrDefault(c, 0) + 1);
    // }
    // int left = 0, right = 0;
    // while (right < s.length()) {
    // char c = s.charAt(right);
    // right++;
    // if (need.containsKey(c)) {
    // window.put(c, window.getOrDefault(c, 0) + 1);
    // if (window.get(c).equals(need.get(c))) {
    // valid++;
    // }
    // }
    // while (right - left >= t.length()) {
    // if (valid == need.size()) {
    // res.add(left);
    // }
    // char d = s.charAt(left);
    // left++;
    // if (need.containsKey(d)) {
    // if (window.get(d).equals(need.get(d))) {
    // valid--;
    // }
    // window.put(d, window.get(d) - 1);
    // }
    // }
    // }
    // return res;
    // }

    // 567
    // public boolean checkInclusion(String t, String s) {
    // Map<Character, Integer> window = new HashMap<>();
    // Map<Character, Integer> need = new HashMap<>();
    // for (char c : t.toCharArray()) {
    // need.put(c, need.getOrDefault(c, 0) + 1);
    // }
    // int valid = 0;
    // int left = 0, right = 0;
    // while (right < s.length()) {
    // char c = s.charAt(right);
    // right++;
    // if (need.containsKey(c)) {
    // window.put(c, window.getOrDefault(c, 0) + 1);
    // if (window.get(c).equals(need.get(c))) {
    // valid++;
    // }
    // }
    // while (right - left >= t.length()) {
    // if (valid == need.size()) {
    // return true;
    // }
    // char d = s.charAt(left);
    // left++;
    // if (need.containsKey(d)) {
    // if (need.get(d).equals(window.get(d))) {
    // valid--;
    // }
    // window.put(d, window.get(d) - 1);
    // }
    // }
    // }
    // return false;
    // }
}
