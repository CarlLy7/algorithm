package hot100;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @description:
 * @author: carl
 * @date: 2025.10.15
 * @Since: 1.0
 */

public class day20251015Solution {
    // [146] LRU 缓存
    class LRUCache {
        private LinkedHashMap<Integer, Integer> map;
        private int capacity;

        public LRUCache(int capacity) {
            map = new LinkedHashMap<>();
            this.capacity = capacity;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                Integer value = map.get(key);
                makeRecently(key);
                return value;
            }
            return -1;
        }

        void put(int key, int value) {
            if (map.containsKey(key)) {
                map.put(key, value);
                makeRecently(key);
                return;
            }
            if (capacity == 0) {
                remove();
            }
            map.put(key, value);
            this.capacity--;
        }

        private void remove() {
            Map.Entry<Integer, Integer> firstNode = map.entrySet().iterator().next();
            map.remove(firstNode.getKey());
            this.capacity++;
        }

        void makeRecently(int key) {
            Integer val = map.get(key);
            map.remove(key);
            map.put(key, val);
        }
    }

    // [460] LFU 缓存
    class LFUCache {
        private HashMap<Integer,Integer> keyToVal;
        private HashMap<Integer,Integer> keyToFre;
        private HashMap<Integer, LinkedHashSet<Integer>> freToKey;
        //容量
        private int cap;
        //最小频率
        private int minFre;
        public LFUCache(int capacity) {
            keyToVal=new HashMap<>();
            keyToFre=new HashMap<>();
            freToKey=new HashMap<>();
            this.cap=capacity;
            this.minFre=0;
        }

        public int get(int key) {
            if (!keyToVal.containsKey(key)){
                return -1;
            }
            Integer value = keyToVal.get(key);
            //增加频次
            increaseFre(key);
            return value;
        }

        public void put(int key, int value) {
            if (this.cap<=0){
                return;
            }
            //存在->更新
            if (keyToVal.containsKey(key)){
                keyToVal.put(key,value);
                increaseFre(key);
                return;
            }
            //不存在->新增
            if (this.cap<=keyToVal.size()){
                removeLeastFre();
            }
            keyToVal.put(key,value);
            keyToFre.put(key,1);
            freToKey.putIfAbsent(1,new LinkedHashSet<>());
            freToKey.get(1).add(key);
            this.minFre=1;
        }

        /**
         * 删除最小频次
         */
        private void removeLeastFre() {
            LinkedHashSet<Integer> keyList = freToKey.get(this.minFre);
            Integer deleteKey = keyList.iterator().next();
            keyList.remove(deleteKey);
            keyToVal.remove(deleteKey);
            keyToFre.remove(deleteKey);
            if (freToKey.get(this.minFre).size()==0){
                freToKey.remove(this.minFre);
            }
        }

        private void increaseFre(int key) {
            Integer fre = keyToFre.get(key);
            keyToFre.put(key, fre + 1);
            freToKey.get(fre).remove(key);
            freToKey.putIfAbsent(fre + 1, new LinkedHashSet<>());
            freToKey.get(fre + 1).add(key);
            if (freToKey.get(fre).size() == 0) {
                freToKey.remove(fre);
                //只有当这个频次没有的时候，才会更新minFre
                if (this.minFre == fre) {
                    this.minFre++;
                }
            }
        }
    }
}
