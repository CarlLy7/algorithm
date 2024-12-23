import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: carl
 * @date: 2024/12/23
 */

public class day20241223Solution {

    //294
    Map<String, Boolean> memo = new HashMap<>();

    public boolean canWin(String currentState) {
        memo.clear();
        return dp(currentState);
    }

    //给定一个字符串s，返回在此情况下先下手是否能赢
    private boolean dp(String s) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        boolean res = false;
        List<String> nextMoves = generatePossibleNextMoves(s);
        for (String next : nextMoves) {
            //因为先手已经走完了，剩下的就是后手走了，只要后手有一个false，说明先手赢了
            boolean canWin = dp(next);
            if (!canWin) {
                res = true;
                break;
            }
        }
        memo.put(s, res);
        return res;
    }

    //293
    public List<String> generatePossibleNextMoves(String currentState) {
        List<String> res = new ArrayList<>();
        char[] charArray = currentState.toCharArray();
        for (int i = 1; i < charArray.length; i++) {
            if (charArray[i] == '+' && charArray[i - 1] == '+') {
                charArray[i - 1] = '-';
                charArray[i] = '-';
                res.add(new String(charArray));
                charArray[i - 1] = '+';
                charArray[i] = '+';
            }
        }
        return res;
    }

    //301
//    List<String> res = new ArrayList<>();
//    StringBuilder track = new StringBuilder();
//
//    public List<String> removeInvalidParentheses(String s) {
//        backTrack(s, 0, 0);
//        int maxLen = 0;
//        for (String re : res) {
//            maxLen = Math.max(maxLen, re.length());
//        }
//        HashSet<String> ans = new HashSet<>();
//        for (String re : res) {
//            if (re.length() == maxLen) {
//                ans.add(re);
//            }
//        }
//        return new ArrayList<>(ans);
//    }
//
//    private void backTrack(String s, int index, int leftCount) {
//        if (index == s.length()) {
//            if (isValid(track.toString())) {
//                res.add(track.toString());
//                return;
//            }
//        }
//        if (s.charAt(index) != '(' && s.charAt(index) != ')') {
//            track.append(s.charAt(index));
//            backTrack(s, index + 1, leftCount);
//            track.deleteCharAt(track.length() - 1);
//        } else {
//            if (leftCount > 0 || s.charAt(index) != ')') {
//                char c = s.charAt(index);
//                track.append(c);
//                if (c == '(') {
//                    leftCount++;
//                } else {
//                    leftCount--;
//                }
//                backTrack(s, index + 1, leftCount);
//                track.deleteCharAt(track.length() - 1);
//                if (c == '(') {
//                    leftCount--;
//                } else {
//                    leftCount++;
//                }
//            } else {
//                backTrack(s, index + 1, leftCount);
//            }
//        }
//    }
//
//    private boolean isValid(String s) {
//        int left = 0;
//        for (char c : s.toCharArray()) {
//            if (c == '(') {
//                left++;
//            } else if (c == ')') {
//                left--;
//                if (left < 0) {
//                    return false;
//                }
//            }
//        }
//        return left == 0;
//    }
}
