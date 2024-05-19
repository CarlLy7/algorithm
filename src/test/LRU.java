package test;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @description: 使用Java来写一个LRU算法
 * @author: lyq
 * @createDate: 21/3/2023
 * @version: 1.0
 */
public class LRU {
    private Integer capacity;
    private LinkedList<Integer> keys;
    private HashMap<Integer,Integer> caches;

    public LRU(Integer capacity) {
        this.capacity = capacity;
        this.keys=new LinkedList<>();
        this.caches=new HashMap<>();
    }
    public Integer get(Integer key){
        Integer value = caches.get(key);
        if(value!=null){
            keys.remove(key);
            keys.addLast(key);
        }
        return value;
    }
    public void put(Integer key,Integer value){
        if(caches.containsKey(key)){
            keys.remove(key);
        }else if(keys.size()>=capacity){
            Integer first = keys.removeFirst();
            caches.remove(first);
        }
        caches.put(key,value);
        keys.addLast(key);
    }
}
