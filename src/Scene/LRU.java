package Scene;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @description: 手写一个LRU算法
 * @author: lyq
 * @createDate: 28/3/2023
 * @version: 1.0
 */
public class LRU {
    Integer capacity = 0;
    LinkedList<Integer> queue = new LinkedList<>();
    Map<Integer, Integer> caches = new HashMap<>();

    public int get(int key) {
        Integer value = caches.get(key);
        if (value != null) {
            queue.remove(key);
            queue.addLast(key);
        }
        return value;
    }

    public void put(int key, int value) {
        if (caches.containsKey(key)) {
            queue.remove(key);
        } else if (caches.size() >= capacity) {
            Integer first = queue.removeFirst();
            caches.remove(first);
        }
        caches.put(key, value);
        queue.addLast(key);
    }

    public LRU(Integer capacity, LinkedList<Integer> queue, Map<Integer, Integer> caches) {
        this.capacity = capacity;
        this.queue = queue;
        this.caches = caches;
    }
}
