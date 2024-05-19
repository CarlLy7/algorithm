package tree;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 扁平化嵌套列表迭代器 https://leetcode.cn/problems/flatten-nested-list-iterator/
 * @author: lyq
 * @createDate: 24/5/2023
 * @version: 1.0
 */
public class NestedIterator implements Iterator<Integer> {
    private LinkedList<NestedInteger> list;
    public NestedIterator(List<NestedInteger> nestedList) {
        list=new LinkedList<>(nestedList);
    }

    @Override
    public Integer next() {
        //因为会先执行hasNext判断有整数之后才会执行next，所以执行next的时候一定是存在整数的
        return list.removeFirst().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!list.isEmpty() && !list.getFirst().isInteger()){
            //不为空，并且第一个元素不是整数是嵌套型的，所以就一直递归执行下去
            List<NestedInteger> first = list.removeFirst().getList();
            for (int i =first.size()-1; i >=0 ; i--) {
                list.addFirst(first.get(i));
            }
        }
        return !list.isEmpty();
    }

}

class NestedInteger {
    private Integer val;
    private List<NestedInteger> list;

    public NestedInteger(Integer val) {
        this.val = val;
        this.list=null;
    }

    public NestedInteger(List<NestedInteger> list) {
        this.list = list;
        this.val=null;
    }
    public boolean isInteger(){
        return val!=null;
    }
    public Integer getInteger(){
        return this.val;
    }

    public List<NestedInteger> getList(){
        return this.list;
    }
}