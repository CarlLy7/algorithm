package day20240707;

/**
 * @description:
 * @author: 小琦
 * @createDate: 2024-07-07 14:56
 * @version: 1.0
 */
public class Trie {
    TrieSet set;

    public Trie() {
        this.set = new TrieSet();
    }

    public void insert(String word) {
        set.add(word);
    }

    public boolean search(String word) {
        return set.contains(word);
    }

    public boolean startsWith(String prefix) {
        return set.hasKeyWithPrefix(prefix);
    }
}

class TrieSet {
    private final TrieMap<Object> map = new TrieMap<>();

    public void add(String key) {
        map.put(key, new Object());
    }


    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public boolean hasKeyWithPrefix(String prefix) {
        return map.hasKeyWithPrefix(prefix);
    }
}

class TrieMap<V> {
    private TrieNode<V> root = null;

    private static class TrieNode<V> {
        V val = null;
        TrieNode<V>[] children = new TrieNode[256];
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }

    public boolean hasKeyWithPrefix(String prefix) {
        return getNode(root, prefix) != null;
    }


    public void put(String key, V val) {
        root = put(root, key, val, 0);
    }

    private TrieNode<V> put(TrieNode<V> node, String key, V val, int i) {
        if (node == null) {
            node = new TrieNode<>();
        }
        if (i == key.length()) {
            node.val = val;
            return node;
        }
        char c = key.charAt(i);
        node.children[c] = put(node.children[c], key, val, i + 1);
        return node;
    }

    public V get(String key) {
        TrieNode<V> x = getNode(root, key);
        if (x == null || x.val == null) {
            return null;
        }
        return x.val;
    }

    private TrieNode<V> getNode(TrieNode<V> node, String key) {
        TrieNode<V> p = node;
        for (int i = 0; i < key.length(); i++) {
            if (p == null) {
                return null;
            }
            char c = key.charAt(i);
            p = p.children[c];
        }
        return p;
    }
}
