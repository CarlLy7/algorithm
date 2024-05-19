package day20240516;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-05-16 22:35
 * @version: 1.0
 */
public class Solution {
    List<String> res = new ArrayList<>();
    LinkedList<String> track = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {
        backTrack(s, 0);
        return res;
    }

    private void backTrack(String s, int start) {
        int length = s.length();
        // 结束条件
        if (start == length && track.size() == 4) {
            res.add(String.join(".", track));
        }
        //遍历选择列表
        for (int i = start; i < length; i++) {
            if (!isValid(s, start, i)) {
                continue;
            }
            if (track.size() >= 4) {
                return;
            }
            //做选择
            track.addLast(s.substring(start, i + 1));
            backTrack(s, i + 1);
            //撤销选择
            track.removeLast();
        }
    }

    private boolean isValid(String s, int start, int end) {
        int len = end - start + 1;
        if (len == 0 || len > 3) {
            return false;
        }
        if (len == 1) {
            return true;
        }
        if (s.charAt(start) == '0') {
            return false;
        }
        if (len <= 2) {
            return true;
        }
        if (Integer.parseInt(s.substring(start, end + 1)) > 255) {
            return false;
        } else {
            return true;
        }
    }
}
