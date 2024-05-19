package array;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @description: 黑名单中的随机数 https://leetcode.cn/problems/random-pick-with-blacklist/
 * @author: lyq
 * @createDate: 7/5/2023
 * @version: 1.0
 */
public class randomPickWithBlacklist {
    //白名单的数量
    int sz=0;
    //key:value value:index
    Map<Integer,Integer> index;
    //我们的思想是将黑名单中的数移动到后面，前面的是白名单中的数，然后进行一个随机返回白名单中的数
    public randomPickWithBlacklist(int n, int[] blacklist) {
        sz=n- blacklist.length;
        index=new HashMap<>();
        //数组中最后一个位置的索引
        int last=n-1;
        //将黑名单中的数全部映射到hashmap中
        for (int black : blacklist) {
            index.put(black,666);
        }
        for (int black : blacklist) {
            //黑名单数组中的数就理解为对应的下标
            if(black>=sz){
                //此时黑名单中的数本身就在后面，直接跳过
                continue;
            }
            //如果此时最后的位置的元素本身就在黑名单中，不做映射往前找
            while(index.containsKey(last)){
                last--;
            }
            //做映射
            index.put(black,last);
            last--;
        }
    }

    public int pick() {
        int res = (int) (Math.random()*sz);
        if(index.containsKey(res)){
            return index.get(res);
        }
        return res;
    }
}
