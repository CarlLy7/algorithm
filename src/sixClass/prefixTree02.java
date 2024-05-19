package sixClass;

import java.util.HashMap;

/**
 * @description:
 * @author: lyq
 * @createDate: 17/3/2023
 * @version: 1.0
 */
public class prefixTree02 {
    class trie{
        class Node{
            private Integer pass;
            private Integer end;
            //Key是ASCII值，value是对应的节点
            private HashMap<Integer,Node> nexts;

            public Node() {
                pass=0;
                end=0;
                nexts=new HashMap<>();
            }
        }
        private Node root;

        public trie() {
            root=new Node();
        }


        public void insert(String word){
            if(word==null){
                return;
            }
            Node node=root;
            node.pass++;
            int path=0;
            char[] array = word.toCharArray();
            for (int i = 0; i < array.length; i++) {
               path=(int) array[i];
                if(!node.nexts.containsKey(path)){
                    node.nexts.put(path,new Node());
                }
                node=node.nexts.get(path);
                node.pass++;
            }
            node.end++;
        }

        public int countEqualTo(String word){
            if(word==null){
                return 0;
            }
            Node node=root;
            char[] array = word.toCharArray();
            int path=0;
            for (int i = 0; i < array.length; i++) {
                path=(int) array[i];
                if(!node.nexts.containsKey(path)){
                    return 0;
                }
                node=node.nexts.get(path);
            }
            return node.end;
        }

        public int getPrefix(String word){
            if(word==null){
                return 0;
            }
            Node node=root;
            int path=0;
            char[] array = word.toCharArray();
            for (int i = 0; i < array.length; i++) {
                path=(int) array[i];
                if(!node.nexts.containsKey(path)){
                    return 0;
                }
                node=node.nexts.get(path);
            }
            return node.pass;
        }

        public void remove(String word){
            if(word==null){
                return;
            }
            if(countEqualTo(word)!=0){
                Node node=root;
                int path=0;
                node.pass--;
                char[] array = word.toCharArray();
                for (int i = 0; i < array.length; i++) {
                    path=(int) array[i];
                    if(--node.nexts.get(path).pass==0){
                        node.nexts.remove(path);
                        return;
                    }
                    node=node.nexts.get(path);
                }
                node.end--;
            }
        }
    }
}
