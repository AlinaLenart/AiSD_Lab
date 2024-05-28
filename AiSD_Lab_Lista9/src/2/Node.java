//import java.util.Comparator;
//
//public class Node<T> {
//    private T[] keys;
//    private Node<T>[] children;
//    private boolean leaf;
//    private int size;
//    private final int t;
//    @SuppressWarnings("unchecked")
//    public Node(int t, boolean leaf) {
//        this.leaf = leaf;
//        this.keys = (T[]) new Object[2 * t - 1];
//        this.children = (Node<T>[]) new Node[2 * t];
//        this.size = 0;
//        this.t = t;
//    }
//    public int binarySearch(T key, Comparator<T> comp) {
//        int left = 0;
//        int right = size - 1;
//
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            int cmp = comp.compare(keys[mid], key);
//
//            if (cmp == 0) {
//                return mid;
//            } else if (cmp < 0) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return -1;
//    }
//    public void insertKey(T k, Comparator<T> comp) {
//        int i = size - 1;
//        while (i >= 0 && comp.compare(k, keys[i]) < 0) {
//            keys[i + 1] = keys[i];
//            i--;
//        }
//        keys[i + 1] = k;
//        increaseSize();
//    }
//    public T[] getKeys() {
//        return keys;
//    }
//
//    public Node[] getChildren() {
//        return children;
//    }
//
//    public boolean isLeaf() {
//        return leaf;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public void setKeys(T[] keys) {
//        this.keys = keys;
//    }
//
//    public void setChildren(Node<T>[] children) {
//        this.children = children;
//    }
//
//    public void setLeaf(boolean leaf) {
//        this.leaf = leaf;
//    }
//
//    public void increaseSize() {
//        size++;
//    }
//    public void decreaseSize() {
//        size--;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//}
