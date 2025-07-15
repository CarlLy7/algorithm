import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: carl
 * @date: 2025.07.15
 * @Since: 1.0
 */

public class day20250715Solution {
    // [LCR 180] 文件组合
    public int[][] fileCombination(int target) {
        // 因为是一个连续正数，并且求和问题，所以标准滑动窗口模板
        int left = 1, right = 1;
        List<int[]> res = new LinkedList<>();
        int windowSum = 0;
        while (right <= target) {
            // 扩大窗口
            windowSum += right;
            right++;
            // 收缩窗口
            while (windowSum > target) {
                windowSum -= left;
                left++;
            }
            // 更新答案
            if (windowSum == target && right - left >= 2) {
                int[] arr = new int[right - left];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = left + i;
                }
                res.add(arr);
            }
        }
        int[][] ans = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    // [LCR 181] 字符串中的单词反转
    public String reverseMessage(String message) {
        if (message.length() == 0) {
            return message;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c != ' ') {
                sb.append(c);
            } else if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ') {
                sb.append(' ');
            }
        }
        // 处理空格之后的字符串
        message = sb.toString();

        // 先整体翻转
        message = reverse(message);

        StringBuilder res = new StringBuilder();
        // 逐个翻转
        for (String s : message.split(" ")) {
            res.append(reverse(s)).append(" ");
        }
        return res.toString().trim();
    }

    /**
     * 翻转字符串
     * 
     * @param message
     * @return
     */
    private String reverse(String message) {
        char[] charArray = message.toCharArray();
        int i = 0, j = charArray.length - 1;
        while (i < j) {
            char temp = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = temp;
            i++;
            j--;
        }
        return new String(charArray);
    }

    // [LCR 182] 动态口令
    public String dynamicPassword(String password, int target) {
        char[] charArray = password.toCharArray();
        StringBuilder sb = new StringBuilder(password);
        for (int i = 0; i < target; i++) {
            sb.append(charArray[i]);
        }
        sb.delete(0, target);
        return sb.toString();
    }
}
