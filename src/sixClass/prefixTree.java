package sixClass;

/**
 * @description: 前缀树
 * @author: lyq
 * @createDate: 17/3/2023
 * @version: 1.0
 */
public class prefixTree {
    class trie{
        class Node{
            private Integer pass;
            private Integer end;
            //这里用数组来表示路径，最多有26条路径就是所有小写字符，如果有更多的路径的话改造成hashmap结构
            private Node[] nexts;
            public Node() {
                this.pass = 0;
                this.end = 0;
                this.nexts = new Node[26];
            }
        }
        private Node root;

        public trie() {
            root=new Node();
        }
        //插入一个字符串到前缀树中
        public void insert(String word){
            if(word==null){
                return;
            }
            char[] array = word.toCharArray();
            Node node=root;
            node.pass++;
            int path=0;//判断这个字符属于哪个路
            for (int i = 0; i < array.length; i++) {
                path=array[i]-'a';
                if(node.nexts[path]==null){
                    node.nexts[path]=new Node();
                }
                node=node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        //查询这个Word在前缀树中出现了多少次，其实可以理解为判断是不是在前缀树中有这个字符串
        public int countEqualTo(String word){
            if(word==null){
                return 0;
            }
            char[] array = word.toCharArray();
            Node node=root;
            int path=0;
            for (int i = 0; i < array.length; i++) {
                path=array[i]-'a';
                if(node.nexts[path]==null){
                    return 0;
                }
                node=node.nexts[path];
            }
            return node.end;
        }

        //查询这个字符串有多少个前缀
        public int getPrefix(String word){
            if(word==null){
                return 0;
            }
            char[] array = word.toCharArray();
            Node node=root;
            int path=0;
            for (int i = 0; i < array.length; i++) {
                path=array[i]-'a';
                if(node.nexts[path]==null){
                    return 0;
                }
                node=node.nexts[path];
            }
            return node.pass;
        }

        //删除指定的字符串
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
                    path=array[i]-'a';
                    if(--node.nexts[path].pass==0){
                        //如果一个节点的pass为0了说明没有访问过了，我们可以断开连接，让jvm进行回收
                        node.nexts[path]=null;
                        return;
                    }
                    node=node.nexts[path];
                }
                node.end--;
            }
        }
    }
}
