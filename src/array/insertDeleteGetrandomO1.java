package array;

import java.util.*;

/**
 * @description: O(1) 时间插入、删除和获取随机元素 https://leetcode.cn/problems/insert-delete-getrandom-o1/
 * @author: lyq
 * @createDate: 7/5/2023
 * @version: 1.0
 */
public class insertDeleteGetrandomO1 {
    //因为插入、删除、随机等概率获取元素都要在O(1)内完成，所以一定是一个数组结构才可以实现O(1)获得元素，至于插入和删除都在数组末尾操作就可以了
    List<Integer> nums;
    //value->index
    Map<Integer,Integer> valueIndex;
    public insertDeleteGetrandomO1() {
        nums=new ArrayList<>();
        valueIndex=new HashMap<>();
    }

    public boolean insert(int val) {
        if(valueIndex.containsKey(val)){
            //如果存在这个元素的话，直接返回false
            return false;
        }
        valueIndex.put(val,nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if(!valueIndex.containsKey(val)){
            //如果不存在这个值的话，直接返回false
            return false;
        }
        //获取这个元素对应的索引
        Integer index = valueIndex.get(val);
        //将最后一个元素的索引换成这个要删除元素的索引
        valueIndex.put(nums.get(nums.size()-1),index);
        //当前元素和最后一个元素进行交换
        Collections.swap(nums,index,nums.size()-1);
        //删掉最后一个元素
        nums.remove(nums.size()-1);
        //删掉索引
        valueIndex.remove(val);
        return true;
    }

    public int getRandom() {
        return nums.get((int) (Math.random()*nums.size()));
    }

}
