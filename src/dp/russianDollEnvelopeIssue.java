package dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description:
 * @author: lyq
 * @createDate: 27/3/2023
 * @version: 1.0
 */
public class russianDollEnvelopeIssue {
    public int maxEnvelopes(int[][] envelopes) {
        //先将宽度进行升序排列，如果宽度相同的话按照高度进行降序排列，最后对高度进行求一个最长递增子序列就可以得到答案
        int n=envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]==o2[0]?o2[1]-o1[1]:o1[0]-o2[0];
            }
        });
        int[] heights=new int[n];
        for (int i = 0; i < n; i++) {
            heights[i]=envelopes[i][1];
        }
        return maxLength(heights);
    }

    private int maxLength(int[] heights) {
        int res=0;
        int[] dp=new int[heights.length];
        Arrays.fill(dp,1);
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < i; j++) {
                if(heights[i]>heights[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            res=Math.max(res,dp[i]);
        }
        return res;
    }
}
