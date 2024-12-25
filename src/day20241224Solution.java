import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: carl
 * @date: 2024/12/24
 */

public class day20241224Solution {

    //254
    public List<List<Integer>> getFactors(int n) {
        return solveSubProblem(n, 2);
    }

    private List<List<Integer>> solveSubProblem(int n, int start) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 3) {
            return res;
        }
        for (int x = start; x <= (int)Math.sqrt(n); x++) {
            if (n % x != 0) {
                continue;
            }
            res.add(new ArrayList<>(Arrays.asList(x, n / x)));
            List<List<Integer>> subProblem = solveSubProblem(n / x, x);
            for (List<Integer> sub : subProblem) {
                // 将第一步操作的x加进去
                sub.add(x);
                res.add(sub);
            }
        }
        return res;
    }

    //267
//    public List<String> generatePalindromes(String s) {
//        int[] count = new int[26];
//        for (char c : s.toCharArray()) {
//            count[c - 'a']++;
//        }
//        //出现奇数次的字母个数
//        int oddCount = 0;
//        for (int i = 0; i < 26; i++) {
//            if (count[i] % 2 == 1) {
//                oddCount++;
//                if (oddCount > 1) {
//                    return new ArrayList<>();
//                }
//            }
//        }
//        //找到唯一的奇数次字母
//        char oddChar = ' ';
//        for (int i = 0; i < 26; i++) {
//            if (count[i] % 2 == 1) {
//                oddChar = (char) (i + 'a');
//                count[i]--;
//                break;
//            }
//        }
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < 26; i++) {
//            for (int j = 0; j < count[i] / 2; j++) {
//                sb.append((char) (i + 'a'));
//            }
//        }
//        permuteUnique(sb.toString());
//        List<String> result = new ArrayList<>();
//        for (String left : res) {
//            String right = new StringBuilder(left).reverse().toString();
//            if (oddChar != ' ') {
//                left += oddChar;
//            }
//            result.add(left + right);
//        }
//        return result;
//    }
//
//    List<String> res = new ArrayList<>();
//    LinkedList<Character> track = new LinkedList<>();
//    boolean[] used;
//
//    private List<String> permuteUnique(String s) {
//        used = new boolean[s.length()];
//        char[] chars = s.toCharArray();
//        Arrays.sort(chars);
//        backTrack(chars);
//        return res;
//    }
//
//    private void backTrack(char[] chars) {
//        if (track.size() == chars.length) {
//            StringBuilder sb = new StringBuilder();
//            for (Character c : track) {
//                sb.append(c);
//            }
//            res.add(sb.toString());
//            return;
//        }
//        for (int i = 0; i < chars.length; i++) {
//            if (used[i]) {
//                continue;
//            }
//            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) {
//                continue;
//            }
//            track.addLast(chars[i]);
//            used[i] = true;
//            backTrack(chars);
//            track.removeLast();
//            used[i] = false;
//        }
//    }

    //291
//    boolean res = false;
//    Map<Character, String> patternToWord = new HashMap<>();
//    Map<String, Character> wordToPattern = new HashMap<>();
//
//    public boolean wordPatternMatch(String pattern, String s) {
//        backTrack(pattern, 0, s);
//        return res;
//    }
//
//    private void backTrack(String pattern, int index, String s) {
//        if (index == pattern.length() && s.length() == 0) {
//            res = true;
//            return;
//        }
//        if (index == pattern.length() || s.length() == 0) {
//            return;
//        }
//        char c = pattern.charAt(index);
//        //有映射
//        if (patternToWord.containsKey(c)) {
//            String word = patternToWord.get(c);
//            if (!s.startsWith(word)) {
//                return;
//            }
//            backTrack(pattern, index + 1, s.substring(word.length()));
//        } else {
//            //没有映射
//            for (int i = 1; i <= s.length(); i++) {
//                String word = s.substring(0, i);
//                if (wordToPattern.containsKey(word)) {
//                    continue;
//                }
//                patternToWord.put(c, word);
//                wordToPattern.put(word, c);
//                backTrack(pattern, index + 1, s.substring(i));
//                patternToWord.remove(c);
//                wordToPattern.remove(word);
//            }
//        }
//    }
}
