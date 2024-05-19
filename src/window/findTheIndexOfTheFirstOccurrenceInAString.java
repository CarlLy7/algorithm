package window;

/**
 * @description: 找出字符串中第一个匹配项的下标 https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * @author: lyq
 * @createDate: 3/5/2023
 * @version: 1.0
 */
public class findTheIndexOfTheFirstOccurrenceInAString {
    //Rabin-Karp算法，字符串匹配算法
    public int strStr(String haystack, String needle) {
        int L = needle.length();
        //256进制
        int R = 256;
        long Q = 1658598167;
        long RL = 1;
        //求的其实是 R ^(L-1) 这个结果，但是为了防止出现溢出，所以使用了循环求N次方的方法
        for (int i = 1; i <= L - 1; i++) {
            RL = (RL * R) % Q;
        }
        long pathHash = 0;
        for (int i = 0; i < needle.length(); i++) {
            pathHash = (pathHash * R + needle.charAt(i)) % Q;
        }
        //计算模式串的hash值
        int left = 0, right = 0;
        long windowHash = 0;
        while (right < haystack.length()) {
            windowHash = ((R * windowHash) % Q + haystack.charAt(right)) % Q;
            right++;
            if (right - left == L) {
                if (windowHash == pathHash) {
                    if (needle.equals(haystack.substring(left, right))) {
                        return left;
                    }
                }
                windowHash = (windowHash - (haystack.charAt(left) * RL) % Q + Q) % Q;
                left++;
            }
        }
        return -1;
    }
}
