import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private Node root;
    private int size;

    private class Node{
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int size;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 1;
        }
    }

    public BSTMap(){
        root = null;
    }

    public BSTMap(K key, V value){
        root.key = key;
        root.value = value;
        root.right = null;
        root.left = null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if(key == null){
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key){
        if(key == null){
            throw new IllegalArgumentException();
        }
        if(node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            return get(node.left, key);
        }else if(cmp > 0){
            return get(node.right, key);
        }else{
            return node.value;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node n){
        if(n == null){
            return 0;
        }
        return n.size;
    }

    @Override
    public void put(K key, V value) {
        if(key == null){
            throw new IllegalArgumentException();
        }
        this.root = put(root, key, value);
    }


    private Node put(Node node, K key, V value){
        Node n = node;
        if(n == null){
            n = new Node(key, value);
        }
        int cmp = key.compareTo(n.key);
        if(cmp < 0){
            n.left = put(n.left, key, value);
        }else if(cmp > 0){
            n.right = put(n.right, key, value);
        }else {
            n.value = value;
        }
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    @Override
    public Set<K> keySet() {
        Set<K> BSTset = new HashSet<>();
        Iterator<K> iterator = this.iterator();
        while(iterator.hasNext()){
            BSTset.add(iterator.next());
        }
        return BSTset;
    }

    @Override
    public V remove(K key) {
        V value = get(key);
        root = remove(root, key);
        return value;
    }

    @Override
    public V remove(K key, V value) {
        root = remove(root, key);
        return value;
    }

    private Node remove(Node root, K key) {
        if(root == null){
            return null;
        }
        root.size -= 1;
        int cmp = key.compareTo(root.key);
        if(cmp < 0){
            root.left = remove(root.left, key);
        }else if(cmp > 0){
            root.right = remove(root.right, key);
        }else{
            if(root.left == null && root.right == null){
                root = null;
            }else if(root.right != null){
                root.value = successor(root).value;
                root.key = successor(root).key;
               // root.size -= 1;
                //root.size = size(root.right) + size(root.left) + 1;
                root.right = remove(root.right, root.key);
                //root.size -= 1;

            }else if(root.left != null){
                root.value = predecessor(root).value;
                root.key = predecessor(root).key;
               // root.size -= 1;
                //root.size = size(root.right) + size(root.left) + 1;
                root.left = remove(root.left, root.key);
                //root.size -= 1;
            }

        }


        return root;
    }

    private Node successor(Node root){
        root = root.right;
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    private Node predecessor(Node root){
        root = root.left;
        while(root.right != null){
            root = root.right;
        }
        return root;
    }


    @Override
    public Iterator<K> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<K>{
        private Queue<Node> queue;

        public BSTIterator(Node n){
            queue = new LinkedList<Node>();
            inOrderTraverse(n, queue);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public K next() {
            return queue.poll().key;
        }

        private void inOrderTraverse(Node n, Queue q){
            if(n == null){
                return;
            }
            inOrderTraverse(n.left, q);
            q.add(n);
            inOrderTraverse(n.right, q);
        }

    }

    public static void main(String[] args){
        BSTMap noChild = new BSTMap();
        noChild.put('Z', 15);
        noChild.remove('Z');
        noChild.size();
        noChild.get('Z');
    }
}
