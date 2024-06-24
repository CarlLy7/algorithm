package day20240621;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-06-21 17:49
 * @version: 1.0
 */
public class Test2 {
    /**
     * 10w合同，id,content
     * "a"   --> "a"
     * "中“ ---》”中“
     */
    //key:a  -> id...
    //set
    class HT {
        private String id;
        private String content;

        public HT(String id, String content) {
            this.id = id;
            this.content = content;
        }
    }

    public void method1(List<HT> htList) {
        List<Set<String>> charList = new ArrayList<>();
        for (HT ht : htList) {
            //构造set
        }
        HashMap<Character, List<String>> map = new HashMap<>();
        for (HT ht : htList) {
            for (char c : ht.content.toCharArray()) {
                map.putIfAbsent(c, new ArrayList<>());
                map.get(c).add(ht.id);
            }
        }
    }
}
